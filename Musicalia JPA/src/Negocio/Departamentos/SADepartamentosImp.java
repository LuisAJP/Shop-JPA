package Negocio.Departamentos;

import Negocio.Empleados.ONEmpleado;
import Negocio.Empleados.TransferEmpleado;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class SADepartamentosImp implements SADepartamentos {
	
	
	public Double calcularNominasDepartamento(Integer id) {
        Double nominaTotal = 0d;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypedQuery<ONEmpleado> query = em.createQuery("SELECT e FROM ONEmpleado e WHERE e.departamento.activo=:activo AND e.departamento.id=:id", ONEmpleado.class);
            query.setParameter("activo", true);
            query.setParameter("id", id);
            for (ONEmpleado empleado : query.getResultList()) {
                em.lock(empleado, LockModeType.OPTIMISTIC);
                nominaTotal += empleado.calcularNomina();
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            nominaTotal = null;
        }

        em.close();
        emf.close();

        return nominaTotal;
	}

	
	public Integer altaDepartamento(TransferDepartamento transferDepartamento) {
	    if (transferDepartamento == null ||
                transferDepartamento.getNombre() == null ||
                transferDepartamento.getActivo() == null) {
	        return null;
        }
        Integer result = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();
		try {
            em.getTransaction().begin();
            TypedQuery<ONDepartamento> query = em.createNamedQuery("Negocio.Departamentos.ONDepartamento.findByNombre", ONDepartamento.class);
            query.setParameter("nombre", transferDepartamento.getNombre());
            ONDepartamento newDepartamento = null;
            try {
                newDepartamento = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
            if (newDepartamento == null || !newDepartamento.getActivo()) {
                if (newDepartamento == null) {
                    newDepartamento = new ONDepartamento();
                    em.persist(newDepartamento);
                }
                newDepartamento.setNombre(transferDepartamento.getNombre());
                newDepartamento.setActivo(transferDepartamento.getActivo());
                em.persist(newDepartamento);
                em.getTransaction().commit();
                result = newDepartamento.getID();
            } else {
                em.getTransaction().rollback();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
        }
		em.close();
		emf.close();
		return result;
	}

	
	public Integer bajaDepartamento(Integer id) {
		Integer result = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();
		try {
            em.getTransaction().begin();
            ONDepartamento departamento = em.find(ONDepartamento.class, id);
            if (departamento != null && departamento.getActivo()) {
                em.lock(departamento, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                if (departamento.getEmpleados().isEmpty()) {
                    departamento.setActivo(false);
                    em.getTransaction().commit();
                } 
                else {
                    em.getTransaction().rollback();
                }
                result = id;
            } else {
                em.getTransaction().rollback();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
        }
		em.close();
		emf.close();
		return result;
	}
	

    public Integer modificarDepartamento(TransferDepartamento transferDepartamento) {
        Integer result = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ONDepartamento departamento = em.find(ONDepartamento.class, transferDepartamento.getID());
            if (departamento != null && departamento.getActivo()) {
	            if (!departamento.getNombre().equals(transferDepartamento.getNombre())) {
	            	TypedQuery<ONDepartamento> query = em.createNamedQuery("Negocio.Departamentos.ONDepartamento.findByNombre", ONDepartamento.class);
	                query.setParameter("nombre", transferDepartamento.getNombre());
	                ONDepartamento departamentoAnterior = null;
	    			try {
	    				departamentoAnterior = query.getSingleResult();
	                    em.getTransaction().rollback();
	                    em.close();
	                    emf.close();
	                    return null;
	                } catch (NoResultException ignored) {
	                }
	            }
                em.lock(departamento, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                departamento.setNombre(transferDepartamento.getNombre());
                departamento.setActivo(transferDepartamento.getActivo());
                result = transferDepartamento.getID();
            }
            else {
                em.getTransaction().rollback();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
        return result;
    }

    
    
	public TransferDepartamento consultarDepartamento(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();
        TransferDepartamento transferDepartamento = null;
		try {
            em.getTransaction().begin();

            ONDepartamento departamento = em.find(ONDepartamento.class, id);

            if (departamento != null && departamento.getActivo()) {
                em.lock(departamento, LockModeType.OPTIMISTIC);

                transferDepartamento = departamento.toTransfer();

                List<TransferEmpleado> empleados = new ArrayList<>(departamento.getEmpleados().size());
                for (ONEmpleado empleado : departamento.getEmpleados()) {
                    em.lock(empleado, LockModeType.OPTIMISTIC);
                    if (empleado.getActivo()) {
                        empleados.add(empleado.toTransfer());
                    }
                }

                transferDepartamento.setEmpleados(empleados);
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
        }

		em.close();
		emf.close();

		return transferDepartamento;
	}

	
	
	public List<TransferDepartamento> listarDepartamentos() {
		List<TransferDepartamento> departamentos = new ArrayList<>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();

		try {
            em.getTransaction().begin();

            TypedQuery<ONDepartamento> query = em.createNamedQuery("Negocio.Departamentos.ONDepartamento.findByActivo", ONDepartamento.class);
            query.setParameter("activo", true);
            for (ONDepartamento departamento : query.getResultList()) {
                em.lock(departamento, LockModeType.OPTIMISTIC);
                departamentos.add(departamento.toTransfer());
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
		    departamentos = null;
        }

		em.close();
		emf.close();

		return departamentos;
	}

}
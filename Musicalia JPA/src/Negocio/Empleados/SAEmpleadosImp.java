package Negocio.Empleados;

import Negocio.Departamentos.ONDepartamento;
import javax.persistence.*;


public class SAEmpleadosImp implements SAEmpleados {

	public Integer altaEmpleado(TransferEmpleado transferEmpleado) {
	    if (transferEmpleado == null ||
                transferEmpleado.getNombre() == null ||
                transferEmpleado.getApellidos() == null ||
                transferEmpleado.getDNI() == null ||
                transferEmpleado.getIDDepartamento() == null ||
                transferEmpleado.getActivo() == null) {
	        return null;
        }
        Integer result = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();

		try {
            em.getTransaction().begin();

            TypedQuery<ONEmpleado> query = em.createNamedQuery("Negocio.Empleados.ONEmpleado.findByDni", ONEmpleado.class);
            query.setParameter("dni", transferEmpleado.getDNI());
            ONEmpleado newEmpleado = null;
            try {
                newEmpleado = query.getSingleResult();

                em.lock(newEmpleado, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            } catch (NoResultException ignored) {
            }

            if ((newEmpleado != null && newEmpleado.getActivo()) ||
                    (newEmpleado instanceof ONEmpleadoTiempoCompleto && transferEmpleado instanceof TransferEmpleadoTiempoParcial) ||
                    (newEmpleado instanceof ONEmpleadoTiempoParcial && transferEmpleado instanceof TransferEmpleadoTiempoCompleto)) {
                em.getTransaction().rollback();
                em.close();
                emf.close();
                return null;
            }

            ONDepartamento departamento = em.find(ONDepartamento.class, transferEmpleado.getIDDepartamento());
            if (departamento == null || !departamento.getActivo()) {
                em.getTransaction().rollback();
                em.close();
                emf.close();
                return null;
            }
            em.lock(departamento, LockModeType.OPTIMISTIC);

            if (transferEmpleado instanceof TransferEmpleadoTiempoCompleto) {
                ONEmpleadoTiempoCompleto newEmpleadoTC;

                if (newEmpleado == null) {
                    newEmpleadoTC = new ONEmpleadoTiempoCompleto();
                    em.persist(newEmpleadoTC);
                } else {
                    newEmpleadoTC = (ONEmpleadoTiempoCompleto) newEmpleado;
                }

                TransferEmpleadoTiempoCompleto transfer = (TransferEmpleadoTiempoCompleto) transferEmpleado;
                newEmpleadoTC.setImpuesto(transfer.getImpuesto());
                newEmpleadoTC.setSalario(transfer.getSalario());

                newEmpleado = newEmpleadoTC;
            } else {
                ONEmpleadoTiempoParcial newEmpleadoTP;

                if (newEmpleado == null) {
                    newEmpleadoTP = new ONEmpleadoTiempoParcial();
                    em.persist(newEmpleadoTP);
                } else {
                    newEmpleadoTP = (ONEmpleadoTiempoParcial) newEmpleado;
                    em.persist(newEmpleadoTP);
                }

                TransferEmpleadoTiempoParcial transfer = (TransferEmpleadoTiempoParcial) transferEmpleado;
                newEmpleadoTP.setHoras(transfer.getHoras());
                newEmpleadoTP.setSalarioPorHora(transfer.getSalarioPorHora());

                newEmpleado = newEmpleadoTP;
            }
            newEmpleado.setNombre(transferEmpleado.getNombre());
            newEmpleado.setApellidos(transferEmpleado.getApellidos());
            newEmpleado.setDNI(transferEmpleado.getDNI());
            newEmpleado.setActivo(transferEmpleado.getActivo());
            newEmpleado.setDepartamento(departamento);

            em.getTransaction().commit();

            result = newEmpleado.getID();
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
        }

		em.close();
		emf.close();

		return result;
	}

	public Integer bajaEmpleado(Integer id) {
		Integer result = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
		EntityManager em = emf.createEntityManager();

		try {
            em.getTransaction().begin();

            ONEmpleado empleado = em.find(ONEmpleado.class, id);
            if (empleado != null && empleado.getActivo()) {
                em.lock(empleado, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

                empleado.setActivo(false);
                empleado.setDepartamento(null);
                result = id;
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
		    em.getTransaction().rollback();
        }

		em.close();
		emf.close();

		return result;
	}

    public Double calcularNomina(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoIndividual");
        EntityManager em = emf.createEntityManager();

        Double nomina = null;

        try {
            em.getTransaction().begin();

            ONEmpleado empleado = em.find(ONEmpleado.class, id);

            if (empleado != null && empleado.getActivo()) {
                em.lock(empleado, LockModeType.OPTIMISTIC);
                nomina = empleado.calcularNomina();
            }

            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        em.close();
        emf.close();

        return nomina;
    }
}
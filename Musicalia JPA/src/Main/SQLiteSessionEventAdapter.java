package Main;

import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;


//Se usa esta clase necesaria para activar foreign keys en SQLite.
public class SQLiteSessionEventAdapter extends SessionEventAdapter {

    @Override
    public void postAcquireClientSession(SessionEvent event) {
        event.getSession().executeNonSelectingSQL("PRAGMA foreign_keys = ON");

        super.postAcquireClientSession(event);
    }

}

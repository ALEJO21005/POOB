import java.time.LocalDate;
import java.util.Collection;
import java.util.Locale;
import java.util.TreeMap;

public class ECISanitas {


    private TreeMap<String, Patient> patients;
    private TreeMap<String, Hospital> hospitals;
    private TreeMap<String, Illness> illnesses;


    /**
     *
     * @param patientId El identificador de un paciente
     * @return  Un objeto de tipo paciente, identificandolo con patientId
     */
    private Patient getPatient(String patientId){
        return patients.get(patientId);
    }

    /**
     *
     * @param hospitalName El identificador(nombre) de un hospital
     * @return Un objeto de tipo Hospital, identificandolo con hospitalName
     */
    private Hospital getHospital(String hospitalName){
        return hospitals.get(hospitalName);
    }

    /**Agendar una cita
     *
     * @param patientId
     * @param hospitalName
     * @param requestedSpeciality
     * @param date
     * @param timeSlot
     */
    public void scheduleAppoiment(String patientId, String hospitalName, String requestedSpeciality, LocalDate date, int timeSlot){
        Patient p = getPatient(patientId);
        Hospital h = getHospital(hospitalName);

        if(!p.equals(null) && !h.equals(null)){
            h.createAppoiment(p, requestedSpeciality, date, timeSlot);
        }
    }
}

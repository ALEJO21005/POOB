import java.time.LocalDate;
import java.util.ArrayList;

public class Hospital {
	private String name;
	private String address;
	private ArrayList<Office> offices;
	private ArrayList<Doctor> doctors;
	private Location location;
	private ArrayList<Treatment> treatments;


	/** Dado el paciente y el doctor genera la cita
	 *
	 * @param p El paciente al cual se le asigno la cita
	 * @param d El doctor que se va a encargar de la cita
	 * @param date La fecha en la que es agendada la cita
	 * @param timeSlot El numero que representa la hora en la que es agendada la cita
	 */

	public void generateAppoiment(Patient p, Doctor d, LocalDate date, int timeSlot){
		Office o = d.getOffice();
		Appointment appointment = new Appointment(d, o,date,timeSlot);
		p.addAppoiment(appointment);
	}

	/** Crea una cita en el hospital para un paciente que solicita unja especialidad en especifico
	 *
	 *  Busca entre todos los doctores para ver quien esta disponible con la especialidad dada
	 * @param p El paciente que necesita la cita
	 * @param requestedSpecialty La especialidad solicitada por el paciente
	 * @param date la fecha que el paciente desea agendar la cita
	 * @param timeSlot El numero que representa la hora de la cita
	 */
	public void createAppoiment(Patient p, String requestedSpecialty, LocalDate date, int timeSlot){

		for(Doctor d: doctors){
			String ds = d.getSpecialty();
			boolean ia = d.isAvailable(date, timeSlot);

			if (ds.equals(requestedSpecialty) && ia){
				generateAppoiment(p, d, date, timeSlot );
			}
		}
	}
}

import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
	private String idCard;
	private String name;
	private String address;
	private LocalDate birthDate;
	private LocalDate registrationDate;
	private ArrayList<Appointment> appointments;
	private MedicalHistory medicalHistory;


	/** Agrega la cita a un paciente
	 *
	 * @param a La cita que se añade
	 */
	public void addAppoiment(Appointment a){
		this.appointments.add(a);
	}
}

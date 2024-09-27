import java.time.LocalDate;
import java.util.ArrayList;

public class Appointment {
	private String id;
	private LocalDate fecha;
	private int time;
	private String reason;
	private Doctor doctor;
	private Office office;
	private ArrayList<Treatment> patientTreatments;
	private ArrayList<PatientIllness> patientIllnesses;

	/** Constructor de la clase Appoiment
	 *
	 * @param d Doctor: Es el doctor asignado a la cita
	 * @param o Office: Es la oficina donde esta asignada la cita
	 * @param date Es la fecha que quedó asignada la cita
	 * @param timeSlot Representa el numero de la franja horaria de la cita (Hora de la cita)
	 */

	public Appointment(Doctor d, Office o, LocalDate date, int timeSlot){
	this.doctor = d;
	this.office = o;
	this.fecha = date;
	this.time = timeSlot;
}


}

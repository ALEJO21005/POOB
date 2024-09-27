import java.time.LocalDate;

public class Doctor {
	private String id;
	private String name;
	private String specialty;
	private String phone;
	private Office office;

	/**
	 *
	 * @return Devuelve la especialidad del doctor
	 */

	public String getSpecialty(){
		return specialty;
	}


	/** Verifica si el doctor esta disponible para la cita
	 *
	 * @param date
	 * @param timeSlot
	 * @return
	 */
	public boolean isAvailable(LocalDate date, int timeSlot){
		return true;
	}

	/**
	 *
	 * @return Devuelve la oficvina del doctor
	 */
	public Office getOffice(){
		return office;
	}
}

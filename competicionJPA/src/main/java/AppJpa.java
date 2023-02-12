import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Atleta;

public class AppJpa {

	static EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	static final Scanner s = new Scanner(System.in);

	public static void opcionesMenu() {
		System.out.println("--------------------------------------");
		System.out.println("Menú");
		System.out.println("--------------------------------------");
		System.out.println("1. Introducir atleta.");
		System.out.println("2. Revisar clasificación.");
		System.out.println("3. Eliminar atleta.");
		System.out.println("4. Modififcar atleta.");
		System.out.println("5. Ver records.");
		System.out.println("6. Salir\n");
		System.out.println("======================================");
	}

	public static void opcionesSubMenu() {
		System.out.println("\n\t1. Hombres.");
		System.out.println("\t2. Mujeres.");
		System.out.println("\t3. Salir");
	}

	public static void insertarAtleta(String fullName, String genre, int age, int benchPress, int deadlift, int squat,
			int total) {

		Atleta atleta = new Atleta();

		atleta.setFullName(fullName);
		atleta.setAge(age);
		atleta.setGenre(genre);
		atleta.setBenchPress(benchPress);
		atleta.setSquat(squat);
		atleta.setDeadlift(deadlift);
		atleta.setTotal(total);

		entity.getTransaction().begin();
		entity.persist(atleta);
		entity.getTransaction().commit();

		System.out.println("¡Insertado con ÉXITO!\n");
		System.out.println("======================================");
	}

	public static void imprimirAtletas(List<Atleta> listaAtletas) {
		System.out.println("\t\nListado de atletas");
		System.out.println("\t----------------------------------------");
		for (Atleta a : listaAtletas) {
			System.out.println("\t" + a);
		}
		System.out.println("\t----------------------------------------");
	}

	public static void borrarAtleta(int id) {
		entity.getTransaction().begin();
		Atleta atletaIdBorrar = entity.find(Atleta.class, id);
		entity.remove(atletaIdBorrar);
		entity.getTransaction().commit();

		System.out.println("Borrado atleta con id: " + id + "\n");
	}

	public static void modificarAtleta(int id, int benchPress, int squat, int deadlift, int total) {

		entity.getTransaction().begin();
		Atleta atleta = entity.find(Atleta.class, id);

		atleta.setBenchPress(benchPress);
		atleta.setSquat(squat);
		atleta.setDeadlift(deadlift);
		atleta.setTotal(total);

		entity.merge(atleta);

		System.out.print("Modificado atleta con id: " + id + "\n");
		entity.getTransaction().commit();
	}

	public static void consultaSelect() {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Atleta a", Atleta.class);
		List<Atleta> listaAtletas = query.getResultList();
		entity.getTransaction().commit();

		imprimirAtletas(listaAtletas);
	}

	public static void consultaSelectPorGeneroH() {
		String genero = "h";
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Atleta a WHERE a.genre = :genero", Atleta.class);
		query.setParameter("genero", genero);
		List<Atleta> listaAtletas = query.getResultList();
		entity.getTransaction().commit();

		imprimirAtletas(listaAtletas);
	}

	public static void consultaSelectPorGeneroM() {
		String genero = "m";
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Atleta a WHERE a.genre = :genero", Atleta.class);
		query.setParameter("genero", genero);
		List<Atleta> listaAtletas = query.getResultList();
		entity.getTransaction().commit();

		imprimirAtletas(listaAtletas);
	}

	public static void filtrarCategoria(String caracter, List<Atleta> listaAtletas) {
		if (caracter.equals("s")) {
			imprimirAtletas(listaAtletas);
		}
	}

	public static void filtrarPorRecord() {
		entity.getTransaction().begin();
		Query query = entity.createQuery("SELECT a FROM Atleta a", Atleta.class);
		List<Atleta> listaAtletas = query.getResultList();
		entity.getTransaction().commit();

//		
//		Si da error cambiar a JRE 1.8, ya que por defecto coge la 1.5
//		
		System.out.println("\n\tRECORD EN PRESS DE BANCA");
		Atleta recordPress = listaAtletas.stream().filter(a -> a.getBenchPress() > 0)
				.max(Comparator.comparingInt(Atleta::getBenchPress)).orElse(null);
		System.out.println("\t" + recordPress);

		System.out.println("\n===============================================");

		System.out.println("\n\tRECORD EN PRESS PESO MUERTO");
		Atleta recordDeadlift = listaAtletas.stream().filter(a -> a.getDeadlift() > 0)
				.max(Comparator.comparingInt(Atleta::getDeadlift)).orElse(null);
		System.out.println("\t" + recordDeadlift);

		System.out.println("\n===============================================");

		System.out.println("\n\tRECORD EN PRESS PESO MUERTO");
		Atleta recordSquat = listaAtletas.stream().filter(a -> a.getSquat() > 0)
				.max(Comparator.comparingInt(Atleta::getSquat)).orElse(null);
		System.out.println("\t" + recordSquat + "\n");

	}

	public static void main(String[] args) {

		Atleta atleta = new Atleta();
		String fullName;
		String genre;
		int opcion;
		int id;
		int age;
		int benchPress;
		int deadlift;
		int squat;
		int total;

		do {
			opcionesMenu();
			System.out.print("\tSeleccione una opción (1-6): ");
			opcion = Integer.parseInt(s.nextLine());

			switch (opcion) {

			case 1:

				System.out.print("\nNombre completo: ");
				fullName = s.nextLine();

				System.out.print("Edad: ");
				age = Integer.parseInt(s.nextLine());

				System.out.print("Sexo: ");
				genre = s.nextLine();

				System.out.print("Puntuación en press de banca: ");
				benchPress = Integer.parseInt(s.nextLine());

				System.out.print("Puntuación en peso muerto: ");
				squat = Integer.parseInt(s.nextLine());

				System.out.print("Puntuación en sentadillas: ");
				deadlift = Integer.parseInt(s.nextLine());

				total = benchPress + squat + deadlift;
				System.out.println("Puntuación total: " + total + "\n");

				insertarAtleta(fullName, genre, age, benchPress, deadlift, squat, total);

				break;

			case 2:
				int opcionSexo;
				String opcionHombreMaster;
				String opcionMujerMaster;
				do {
					opcionesSubMenu();
					System.out.print("\tSeleccione una opción (1-3): ");
					opcionSexo = Integer.parseInt(s.nextLine());

					switch (opcionSexo) {
					case 1:

						consultaSelectPorGeneroH();

						entity.getTransaction().begin();
						Query queryHombreMaster = entity.createQuery(
								"SELECT a FROM Atleta a WHERE a.age >= 32 AND a.genre = 'h'", Atleta.class);
						List<Atleta> listaAtletasHombresMaster = queryHombreMaster.getResultList();
						entity.getTransaction().commit();

						System.out.print("\n¿Filtrar por categoría máster? s/n: ");
						opcionHombreMaster = s.nextLine();

						filtrarCategoria(opcionHombreMaster, listaAtletasHombresMaster);

						break;
					case 2:
						consultaSelectPorGeneroM();

						entity.getTransaction().begin();
						Query queryMujerMaster = entity.createQuery(
								"SELECT a FROM Atleta a WHERE a.age >= 32 AND a.genre = 'm'", Atleta.class);
						List<Atleta> listaAtletasMujeresMaster = queryMujerMaster.getResultList();
						entity.getTransaction().commit();

						System.out.print("\n¿Filtrar por categoría máster? s/n: ");
						opcionMujerMaster = s.nextLine();

						filtrarCategoria(opcionMujerMaster, listaAtletasMujeresMaster);

						break;

					case 3:
						System.out.println("\nSALIENDO\n");
						break;

					default:
						System.out.println("No existe esa opción. Introduzca otro número.");
						break;
					}

				} while (opcionSexo != 3);

				break;

			case 3:
				consultaSelect();

				System.out.print("Escriba un id para borrar un atleta: ");
				id = Integer.parseInt(s.nextLine());
				borrarAtleta(id);

				break;

			case 4:
				consultaSelect();

				System.out.print("Escriba un id para modificar un atleta: ");
				id = Integer.parseInt(s.nextLine());

				System.out.print("Puntuación en press de banca: ");
				benchPress = Integer.parseInt(s.nextLine());

				System.out.print("Puntuación en peso muerto: ");
				squat = Integer.parseInt(s.nextLine());

				System.out.print("Puntuación en sentadillas: ");
				deadlift = Integer.parseInt(s.nextLine());

				total = benchPress + squat + deadlift;
				System.out.println("Puntuación total: " + total + "\n");

				modificarAtleta(id, benchPress, squat, deadlift, total);

				break;

			case 5:
				filtrarPorRecord();
				break;

			case 6:
				System.out.println("FIN DE LA APLICACIÓN");
				break;

			default:
				System.out.println("No existe esa opción. Introduzca otro número.");
				break;
			}
		} while (opcion != 6);

		entity.close();
	}

}

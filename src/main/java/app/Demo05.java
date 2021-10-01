package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
		// 1. especificar la conexión de BD - DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");

		// 2. Obtener el DAO
		EntityManager em = fabrica.createEntityManager();

		// procesos...obtener la info del usuario con código 12 / para eliminarlo
		Usuario u = em.find(Usuario.class, 12);
		
		if (u == null) {
			System.out.println("Usuario NO existe");
		} else {
			System.out.println("Usuario encontrado : " + u.getNombre());
			System.out.println(u);
			em.getTransaction().begin();
			em.remove(u); // eliminar 
			em.getTransaction().commit();
			System.out.println("Eliminación Ok");
		}
		
		em.close();

	}
}

package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		// 1. especificar la conexión de BD - DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");

		// 2. Obtener el DAO
		EntityManager em = fabrica.createEntityManager();

		// procesos...eliminar el usuario con código 12
		Usuario u = new Usuario();
		u.setCodigo(12);
		// u.setNombre(nombre);
		// u.s.....;

		// reg, act, elim -> Transacciones

		try {
			em.getTransaction().begin();
			em.remove(u); // eliminar 
			em.getTransaction().commit();
			System.out.println("Eliminación Ok");
		} catch (Exception e) {
			System.out.println("Error : " + e.getClass().getTypeName());
		}
		em.close();

	}
}

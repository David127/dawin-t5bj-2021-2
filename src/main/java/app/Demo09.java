package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Usuario;

public class Demo09 {
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	
		EntityManager em = fabrica.createEntityManager();
	
		// Usuario u = em.find(Usuario.class, 12); // Busca segun PK
		// --validar usando usuario y clave
		//String sql = "{call usp_validaAcceso (:xusr, :xcla)}";
		String sql = "{call usp_validaAcceso (?, ?)}";
		// TypedQuery<Usuario> query3 = em.createQuery(sql, Usuario.class); JPA
		
		Query query3 = em.createNativeQuery(sql, Usuario.class); // Object
		//query3.setParameter("xusr", "U002@gmail.com");
		//query3.setParameter("xcla", "10003");
		
		query3.setParameter(1, "U002@gmail.com");
		query3.setParameter(2, "10002");
		
		Usuario u = null;
		try {
			u = (Usuario) query3.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		if (u == null) {
			System.out.println("Usuario NO existe");
		} else {
			System.out.println("Usuario encontrado : " + u.getNombre());
			System.out.println(u);
		}
		
		em.close();
	}
}

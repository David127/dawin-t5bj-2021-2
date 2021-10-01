package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {
	
	public static void main(String[] args) {
	// 1. especificar la conexión de BD - DAOFactory
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");

	// 2. Obtener el DAO
	EntityManager em = fabrica.createEntityManager();
	
	// Listado de los usuarios
	
	TypedQuery<Usuario> query = em.createQuery("Select u from Usuario u", Usuario.class);
	
	List<Usuario> lstUsuarios = query.getResultList();
	
	System.out.println("Cantidad de usuarios: " + lstUsuarios.size());
	
	if (lstUsuarios.size() == 0) {
		System.out.println("Listado vacio");
	} else {
		System.out.println("Listado de Usuarios");
		for (Usuario u : lstUsuarios) {
			System.out.println(">>> " + u);
		}
	}
	
	// Listado de los usuarios, sin usar TypedQuery / query
	List<Usuario> lstUsuarios2 = em.createQuery("Select u from Usuario u", Usuario.class).getResultList();
	
	System.out.println("Cantidad de usuarios: " + lstUsuarios2.size());
	
	if (lstUsuarios2.size() == 0) {
		System.out.println("Listado vacio");
	} else {
		System.out.println("-- Listado de Usuarios --");
		for (Usuario u : lstUsuarios2) {
			System.out.println(">>> " + u);
		}
	}
	
	// Listado con parametros -> listado de los usuarios x tipo
	
	String sql = "Select u from Usuario u where u.tipo = :xtipo";
	
	TypedQuery<Usuario> query3 = em.createQuery(sql, Usuario.class);
	query3.setParameter("xtipo", 2);
	
	List<Usuario> lstUsuarios3 = query3.getResultList();
	
	System.out.println("Cantidad de usuarios: " + lstUsuarios3.size());
	
	System.out.println("-- Listado de Usuarios tipo 1 --");
	for (Usuario u : lstUsuarios3) {
		System.out.println(">>> " + u);
	}
	
	em.close();

	}
}

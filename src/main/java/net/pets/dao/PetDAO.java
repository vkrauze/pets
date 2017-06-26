package net.pets.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.pets.model.Pet;
import net.pets.util.Util;

/**
 * Pet DAO class.
 */
public class PetDAO {
	
	private String myBatisConfigName = "mybatis-config.xml";
	private Util util;
	//+
	private SqlSession sqlSession;
	
	/**
	 * Get List of pets
	 * @return list of all pets
	 */
	public List<Pet> getPets() throws IOException {
		Reader reader = Resources.getResourceAsReader(myBatisConfigName);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			List<Pet> pets = session.selectList("Pet.getAll");
			return pets;
		}		
		// return sqlSession.selectList("Pet.getAll");
	}

	/**
	 * Delete a pet with the id passed as parameter
	 * @param id
	 */
	public void deletePet(int id) throws IOException {
		Reader reader = Resources.getResourceAsReader(myBatisConfigName);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.delete("Pet.deleteById", id);
			session.commit();
		}		
		// sqlSession.delete("Pet.deleteById", id);
	}
	
	/**
	 * Create a new Pet on the DB
	 * @param newPet
	 * @return pet added to DB
	 * @throws ParseException 
	 */
	public Pet addPet(Pet newPet) throws ParseException, IOException {
		Reader reader = Resources.getResourceAsReader(myBatisConfigName);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.insert("Pet.insert", newPet);
			session.commit();
			return newPet;
		}
		// sqlSession.insert("Pet.insert", newPet);
		// return newPet;
	}
	
	/**
	 * Update a current Pet on the DB
	 * @param updatedPet
	 * @return updated pet
	 * @throws ParseException 
	 */
	public Pet updatePet(Pet updatedPet) throws ParseException, IOException {
		Reader reader = Resources.getResourceAsReader(myBatisConfigName);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			session.update("Pet.update", updatedPet);
			session.commit();
			return updatedPet;
		}			
		// sqlSession.update("Pet.update", updatedPet);
		// return updatedPet;
	}
	
	/**
	 * Spring use - DI
	 * @param util
	 */
	public void setUtil(Util util) {
		this.util = util;
	}
	
	//+
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
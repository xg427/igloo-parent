package fr.openwide.jpa.test.cascades;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import fr.openwide.core.jpa.exception.SecurityServiceException;
import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.jpa.example.business.company.model.Company;
import fr.openwide.jpa.example.business.person.model.Person;
import fr.openwide.jpa.test.AbstractJpaTestCase;

public class TestCascades extends AbstractJpaTestCase {
	
	@Test
	public void testCreate() throws ServiceException, SecurityServiceException {
		Company company = new Company("Company Test Persist");
		Company company1 = new Company("Company Test Persist 1");
		Company company2 = new Company("Company Test Persist 2");
		Company company3 = new Company("Company Test Persist 3");
		Company company4 = new Company("Company Test Persist 4");
		Company company5 = new Company("Company Test Persist 5");
		Company company6 = new Company("Company Test Persist 6");
		
		Person person = new Person("Persist", "Numéro");
		Person person1 = new Person("Persist1", "Numéro1");
		Person person2 = new Person("Persist2", "Numéro2");
		Person person3 = new Person("Persist3", "Numéro3");
		Person person4 = new Person("Persist4", "Numéro4");
		Person person5 = new Person("Persist5", "Numéro5");
		Person person6 = new Person("Persist6", "Numéro6");
		
		/* Aucune Cascade (avec une table Join) :
		 * 
		 * Lorsque l'on tente de créer la Company liée à une Person non persisté, 
		 * on déclenche une exception. Il faut absolument que la Person existe dans
		 * la base pour remplir correctement la table Join de cette relation. Il faut 
		 * donc créer la Person avant de créer la Company.
		 */
		company.addEmployee(person);
		try {
			companyService.create(company);
			Assert.fail("La création d'une entité liée à un élément non persisté lève une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(companyService.list().contains(company));
			Assert.assertFalse(personService.list().contains(person));
		}
		
		company = new Company("Company Test Persist");
		person = new Person("Persist", "Numéro");
		
		company.addEmployee(person);
		
		personService.create(person);
		companyService.create(company);
		Assert.assertTrue(companyService.list().contains(company));
		Assert.assertTrue(personService.list().contains(person));
		
		/* Cascade SAVE_UPDATE :
		 * 
		 * La méthode create() utilise la méthode save(). La cascade
		 * SAVE_UPDATE est donc activée lors de la création de la 
		 * Company et la personne non persisté est crée. La table Join
		 * peut donc être remplie avec l'id de la Person persistée.
		 */
		company1.addEmployee1(person1);
		
		companyService.create(company1);
		Assert.assertTrue(companyService.list().contains(company1));
		Assert.assertTrue(personService.list().contains(person1));
		
		/* Cascade PERSIST :
		 * 
		 * La méthode create() provoque un persist jpa ; on a donc cascade de la création de l'utilisateur
		 */
		company2.addEmployee2(person2);
		companyService.create(company2);
		Assert.assertTrue(companyService.list().contains(company2));
		Assert.assertTrue(personService.list().contains(person2));
		
		/* Cascade REMOVE :
		 * 
		 * La méthode create() ne déclenchent pas la cascade
		 * REMOVE. Lorsque l'on tente de créer la Company liée à une 
		 * Person non persisté, on déclenche une exception. Il faut absolument que 
		 * la Person soit persistée dans la base pour remplir correctement la table 
		 * Join de cette relation. Il faut créer la Person avant de créer la Company.
		 */
		company3.addEmployee3(person3);
		try {
			companyService.create(company3);
			Assert.fail("La création d'une entité liée à un élément non persisté lève une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(companyService.list().contains(company3));
		}
		
		/* Cascade DELETE :
		 * 
		 * La méthode create() ne déclenchent pas la cascade
		 * DELETE. Lorsque l'on tente de créer la Company liée à une 
		 * Person non persisté, on déclenche une exception. Il faut absolument que 
		 * la Person soit persistée dans la base pour remplir correctement la table 
		 * Join de cette relation. Il faut créer la Person avant de créer la Company.
		 */
		company4.addEmployee4(person4);
		
		try {
			companyService.create(company4);
			Assert.fail("La création d'une entité liée à un élément non persisté lève une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(companyService.list().contains(company4));
		}
		
		/* Cascade DELETE_ORPHAN :
		 * 
		 * La méthode create() ne déclenchent pas la cascade
		 * DELETE_ORPHAN. Lorsque l'on tente de créer la Company liée à une 
		 * Person non persisté, on déclenche une exception. Il faut absolument que 
		 * la Person soit persistée dans la base pour remplir correctement la table 
		 * Join de cette relation. Il faut créer la Person avant de créer la Company.
		 */
		company5.addEmployee5(person5);
		
		try {
			companyService.create(company5);
			Assert.fail("La création d'une entité liée à un élément non persisté lève une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(companyService.list().contains(company5));
		}
		
		/* Cascade MERGE :
		 * 
		 * La méthode create() ne déclenchent pas la cascade
		 * MERGE. Lorsque l'on tente de créer la Company liée à une 
		 * Person non persisté, on déclenche une exception. Il faut absolument que 
		 * la Person soit persistée dans la base pour remplir correctement la table 
		 * Join de cette relation. Il faut créer la Person avant de créer la Company.
		 */
		company6.addEmployee6(person6);
		
		try {
			companyService.create(company6);
			Assert.fail("La création d'une entité liée à un élément non persisté lève une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(companyService.list().contains(company6));
		}
	}
	
	@Test
	public void testUpdate() throws ServiceException, SecurityServiceException {
		Company company = createCompany("Company Test Update");

		Person person = new Person("Update", "Numéro");
		Person person1 = new Person("Update1", "Numéro1");
		Person person2 = new Person("Update2", "Numéro2");
		Person person3 = new Person("Update3", "Numéro3");
		Person person4 = new Person("Update4", "Numéro4");
		Person person5 = new Person("Update5", "Numéro5");
		Person person6 = new Person("Update6", "Numéro6");

		/* Aucune Cascade (avec une table Join) :
		 * 
		 * Lorsque l'on update la Company, la Person non persistée n'est pas crée
		 * puisqu'il n'y a aucune cascade sur la relation. On a une exception car
		 * on essaye d'ajouter une personne qui n'est pas persistée.
		 */
		company.addEmployee(person);

		try {
			companyService.update(company);
			Assert.fail("Le fait d'updater et d'avoir un élément non persisté lié à l'entité doit provoquer une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(personService.list().contains(person));
		}

		company = companyService.getById(company.getId());
		personService.create(person);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person));
		
		/* Cascade SAVE_UPDATE :
		 * 
		 * Lorsque l'on update la Company, la cascade SAVE_UPDATE est déclanchée et la
		 * Person non persistée est crée dans la base. 
		 */
		company.addEmployee1(person1);
		
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person1));
		
		/* Cascade PERSIST : 
		 * 
		 * Lorsque l'on update, la cascade PERSIST est déclenchée à la sortie du scope transactionnel (proxy companyService.*)
		 */
		company.addEmployee2(person2);

		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person2));

		personService.create(person2);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person2));
		
		/* Cascade REMOVE : 
		 * 
		 * Lorsque l'on update, la cascade PERSIST n'est pas déclanchée. On a une exception
		 * puisque l'on esssaye d'ajouter à la Company une Person qui n'est pas persistée.
		 */
		company.addEmployee3(person3);

		try {
			companyService.update(company);
			Assert.fail("Le fait d'updater et d'avoir une cascade REMOVE sur un élément non persisté doit provoquer une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(personService.list().contains(person3));
		}
		
		company = companyService.getById(company.getId());
		personService.create(person3);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person3));
		
		/* Cascade DELETE : 
		 * 
		 * Lorsque l'on update, la cascade REMOVE n'est pas déclanchée. On a une exception
		 * puisque l'on esssaye d'ajouter à la Company une Person qui n'est pas persistée.
		 */
		company.addEmployee4(person4);

		try {
			companyService.update(company);
			Assert.fail("Le fait d'updater et d'avoir une cascade DELETE sur un élément non persisté doit provoquer une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(personService.list().contains(person4));
		}

		company = companyService.getById(company.getId());
		personService.create(person4);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person4));
		
		/* Cascade DELETE_ORPHAN : 
		 * 
		 * Lorsque l'on update, la cascade PERSIST n'est pas déclanchée. On a une exception
		 * puisque l'on esssaye d'ajouter à la Company une Person qui n'est pas persistée.
		 */
		company.addEmployee5(person5);

		try {
			companyService.update(company);
			Assert.fail("Le fait d'updater et d'avoir une cascade DELETE_ORPHAN sur un élément non persisté doit provoquer une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(personService.list().contains(person5));
		}

		company = companyService.getById(company.getId());
		personService.create(person5);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person5));

		/* Cascade MERGE : 
		 * 
		 * Lorsque l'on update, la cascade PERSIST n'est pas déclanchée. On a une exception
		 * puisque l'on esssaye d'ajouter à la Company une Person qui n'est pas persistée.
		 */
		company.addEmployee6(person6);

		try {
			companyService.update(company);
			Assert.fail("Le fait d'updater et d'avoir une cascade MERGE sur un élément non persisté doit provoquer une exception");
		} catch (InvalidDataAccessApiUsageException e) {
			Assert.assertFalse(personService.list().contains(person6));
		}

		company = companyService.getById(company.getId());
		personService.create(person6);
		companyService.update(company);
		Assert.assertTrue(personService.list().contains(person6));
	}
	
	@Test
	public void testDelete() throws ServiceException, SecurityServiceException {
		Company company = createCompany("Company Test Delete");
		Company company1 = createCompany("Company Test Delete 1");
		Company company2 = createCompany("Company Test Delete 2");
		Company company3 = createCompany("Company Test Delete 3");
		Company company4 = createCompany("Company Test Delete 4");
		Company company5 = createCompany("Company Test Delete 5");
		Company company6 = createCompany("Company Test Delete 6");
		
		Person person = createPerson("Delete", "Numéro");
		Person person1 = createPerson("Delete1", "Numéro1");
		Person person2 = createPerson("Delete2", "Numéro2");
		Person person3 = createPerson("Delete3", "Numéro3");
		Person person4 = createPerson("Delete4", "Numéro4");
		Person person5 = createPerson("Delete5", "Numéro5");
		Person person6 = createPerson("Delete6", "Numéro6");
		
		/* Aucune Cascade (avec table Join) :
		 * 
		 * Lorsque l'on essaye de supprimer la Company qui est liée à la Person,
		 * la relation est supprimée mais l'objet Person est conservé. 
		 */
		company.addEmployee(person);
		companyService.update(company);
		personService.update(person);
		Assert.assertTrue(companyService.list().contains(company));
		
		companyService.delete(company);
		Assert.assertFalse(companyService.list().contains(company));
		Assert.assertTrue(personService.list().contains(person));

		/* Cascade SAVE_UPDATE :
		 * 
		 * Comme attendu, lors de la suppression de la Company, la cascade
		 * SAVE_UPDATE n'est pas déclenchée et la Person liée n'est pas supprimée.
		 */
		//company1.addEmployee1(person1);
		companyService.update(company1);
		Assert.assertTrue(companyService.list().contains(company1));
		
		companyService.delete(company1);
		Assert.assertFalse(companyService.list().contains(company1));
		Assert.assertTrue(personService.list().contains(person1));
		
		
		/* Cascade PERSIST :
		 * 
		 * Comme attendu, lors de la suppression de la Company, la cascade
		 * PERSIST n'est pas déclenchée et la Person liée n'est pas supprimée.
		 */
		company2.addEmployee2(person2);
		companyService.update(company2);
		Assert.assertTrue(companyService.list().contains(company2));
		
		companyService.delete(company2);
		Assert.assertFalse(companyService.list().contains(company2));
		Assert.assertTrue(personService.list().contains(person2));

		
		/* Cascade REMOVE : 
		 * 
		 *  Même en utilisant delete(), la cascade REMOVE est activée 
		 *  et la Person liée à la Company est supprimée en cascade. 
		 */
		company3.addEmployee3(person3);
		companyService.update(company3);
		Assert.assertTrue(companyService.list().contains(company3));
		
		companyService.delete(company3);
		Assert.assertFalse(companyService.list().contains(company3));
		Assert.assertFalse(personService.list().contains(person3));

		
		/* Cascade DELETE
		 * 
		 * Dans cet exemple, on lie la Person à deux Company différentes,
		 * et on observe ce qui va se passer si on essaye de supprimer l'une 
		 * des Company.
		 */
		company4.addEmployee4(person4);
		companyService.update(company4);
		company5.addEmployee1(person4);
		companyService.update(company5);
		
		/* 
		 * Code qu'on avait avant : si jamais l'entité était encore attachée par ailleurs, la base renvoyait une exception
		 * et du coup, ça donnait une indication.
		 * 
		 * Cependant, il semble que l'esprit de la norme JPA dit que si un objet à supprimer par cascade est attaché
		 * par ailleurs, la suppression doit être purement et simplement ignorée...
		 * Ca ne va pas nous aider à trouver les soucis mais, après discussion avec les gens d'Hibernate, ça restera comme cela...
		 *
		try {
			companyService.delete(company4);
			Assert.fail("Supprimer en cascade une entité qui à encore des relations lève une exception");
		} catch (JpaObjectRetrievalFailureException e) {
			// La tentative de suppression de la personne viole une contrainte d'intégrité
			// de la base de données. On ne peut pas supprimer en cascade une Person encore 
			// en relation avec d'autres Company. Pour parer à ce problème, pas de solution
			// automatique, on doit délier l'entité pour pouvoir la supprimer en cascade.
			company4 = companyService.getById(company4.getId());
			company5 = companyService.getById(company5.getId());
			Assert.assertTrue(companyService.list().contains(company4));
			Assert.assertTrue(personService.list().contains(person4));
		}*/
		
		// on délie toute les relations et on doit donc pouvoir supprimer supprimer person4 par cascade sans souci
		person4 = personService.getById(person4.getId());
		company5.removeEmployee1(person4);
		companyService.update(company5);
		companyService.delete(company4);
		Assert.assertNull(personService.getById(person4.getId()));
	
		/* Une fois l'entité Person déliée de la seconde Company, elle est supprimée sans
		 * problèmes par la cascade.
		 */
		Assert.assertFalse(companyService.list().contains(company4));
		Assert.assertFalse(personService.list().contains(person4));

		
		/* Cascade DELETE_ORPHAN :
		 * 
		 * La cascade DELETE_ORPHAN (ou orphanRemoval) va détruire, en plus des entités liées 
		 * par la relation, les entités qui sont référencés comme anciennement liés. Pour mettre
		 * se comportement en valeur, on lie une Person une Company puis on détruit le lien. 
		 * Lorsque l'on supprime la Company, on constate que la Person est aussi détruite
		 */
		person5 = personService.getById(person5.getId());
		company5.addEmployee5(person5);
		companyService.update(company5);
		Assert.assertTrue(companyService.list().contains(company5));
		
		companyService.delete(company5);
		Assert.assertFalse(companyService.list().contains(company5));
		Assert.assertFalse(personService.list().contains(person5));

		
		/* Cascade MERGE :
		 * 
		 * Comme attendu, lors de la suppression de la Company, la cascade
		 * MERGE n'est pas déclenchée et la Person liée n'est pas supprimée.
		 */
		person6 = personService.getById(person6.getId());
		company6 = companyService.getById(company6.getId());
		company6.addEmployee6(person6);
		companyService.update(company6);
		Assert.assertTrue(companyService.list().contains(company6));
		
		companyService.delete(company6);
		Assert.assertFalse(companyService.list().contains(company6));
		Assert.assertTrue(personService.list().contains(person6));
	}
}

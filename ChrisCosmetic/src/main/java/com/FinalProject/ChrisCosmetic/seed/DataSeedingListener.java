package com.FinalProject.ChrisCosmetic.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.entity.Category;
import com.FinalProject.ChrisCosmetic.entity.Role;
import com.FinalProject.ChrisCosmetic.entity.SubCategory;
import com.FinalProject.ChrisCosmetic.repository.AccountRepository;
import com.FinalProject.ChrisCosmetic.repository.CategoryRepository;
import com.FinalProject.ChrisCosmetic.repository.RoleRepository;
import com.FinalProject.ChrisCosmetic.repository.SubCategoryRepository;

import java.util.UUID;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//Category
		if(!categoryRepository.findByCategoryName("Makeup").isPresent()) {
			categoryRepository.save(new Category("Makeup"));
		}
		if(!categoryRepository.findByCategoryName("Skincare").isPresent()) {
			categoryRepository.save(new Category("Skincare"));
		}
		if(!categoryRepository.findByCategoryName("Body care").isPresent()) {
			categoryRepository.save(new Category("Body care"));
		}

		//SubCategory
		if(!subCategoryRepository.findBySubCategoryName("Face").isPresent()) {
			subCategoryRepository.save(new SubCategory("Face", categoryRepository.findByCategoryName("Makeup").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Lip").isPresent()) {
			subCategoryRepository.save(new SubCategory("Lip", categoryRepository.findByCategoryName("Makeup").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Eyes").isPresent()) {
			subCategoryRepository.save(new SubCategory("Eyes", categoryRepository.findByCategoryName("Makeup").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Cleansing").isPresent()) {
			subCategoryRepository.save(new SubCategory("Cleansing", categoryRepository.findByCategoryName("Skincare").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Mask", categoryRepository.findByCategoryName("Skincare").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Skin").isPresent()) {
			subCategoryRepository.save(new SubCategory("Skin", categoryRepository.findByCategoryName("Skincare").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Skin Body").isPresent()) {
			subCategoryRepository.save(new SubCategory("Skin Body", categoryRepository.findByCategoryName("Body care").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Hair").isPresent()) {
			subCategoryRepository.save(new SubCategory("Hair", categoryRepository.findByCategoryName("Body care").get()));
		}

		//Role
		if(roleRepository.findByRoleName("ADMIN") == null){
			roleRepository.save(new Role("1", "ADMIN"));
		}
		if(roleRepository.findByRoleName("EMPLOYEE") == null){
			roleRepository.save(new Role("2", "EMPLOYEE"));
		}
		if(roleRepository.findByRoleName("CUSTOMER") == null){
			roleRepository.save(new Role("3", "CUSTOMER"));
		}

		//Admin account
//		if(!accountRepository.findByEmail("admin@gmail.com").isPresent()) {
//			Account admin = new Account();
//			admin.setEmail("admin@gmail.com");
//		}
	}
}
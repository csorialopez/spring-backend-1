/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.*;
import com.sales.market.service.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final PositionService positionService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    SubCategory cleanSubCategory = null;

    public DevelopmentBootstrap(PositionService positionService, CategoryService categoryService,
                                SubCategoryService subCategoryService, EmployeeService employeeService,
                                ClientService clientService) {
        this.positionService = positionService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        persistCategoriesAndSubCategories();
        persistPositions();
        createEmployee();
        inicializeClients();
    }

    private void inicializeClients(){
        persistClient("Jose", "Vargas", "jose@vargas.com", "974878878" );
        persistClient("Raul", "Martinez", "raul@martins.com", "92132878" );
    }
    private Client persistClient(String name, String lastName, String email, String cellphone){
        Client client = new Client();
        client.setName(name);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setCellphone(cellphone);
        client.setPoints(0);
        return clientService.save(client);
    }
    private void createEmployee() {
        persistEmployee("Raul", "Martinez", "raulm@gmail.com");
        persistEmployee("Clara", "Soria", "arturo@gmail.com"); //Gerente
        persistEmployee("Henry", "Claros", "henry@gmail.com");
        persistEmployee("System", "", "edson@gmail.com");
    }


    private String getResourceAsString(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void persistPositions() {
        positionService.save(new Position("Gerente"));
        positionService.save(new Position("Cajero"));
        positionService.save(new Position("Acomodador"));
        positionService.save(new Position("Almacenero"));
    }
    private void persistCategoriesAndSubCategories() {
        Category category = persistCategory("LIMPIEZA", "LIM");
        Category category1 = persistCategory("SALUD", "SAL");
        persistSubCategory("DETERGENTES", "LIM-DET", category);
        persistSubCategory("INYECCIONES","SAL-INY",category1);
        cleanSubCategory = persistSubCategory("JABONES", "JABON-CODE", category);
    }
    private Employee persistEmployee(String firstName, String lastName, String email) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        return employeeService.save(employee);
    }

    private SubCategory persistSubCategory(String name, String code, Category category) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCode(code);
        subCategory.setCategory(category);
        return subCategoryService.save(subCategory);
    }
    private Category persistCategory(String name, String code) {
        Category category = new Category();
        category.setName(name);
        category.setCode(code);
        return categoryService.save(category);
    }
}

package com.sales.market.service;

import com.sales.market.exception.GenericException;
import com.sales.market.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaleServiceTest {
    @Autowired
    private BuyService buyService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemInstanceService itemInstanceService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private SaleService saleService;

    @Test
    public void testWhenRegisterSaleOfItemInstances(){
        Set<ItemInstance> itemInstances =  createItemInstanceList();
        Position gerentePosition = positionService.save(new Position("Gerente"));
        Position cajeroPosition = positionService.save(new Position("Cajero"));
        Employee cajero = createEmployee("Jose", "Vidal", "joser@gmail.com", cajeroPosition);
        createEmployee("Clara", "Soria", "csorialopez11@gmail.com", gerentePosition);
        Client client1 = createClient("Raul", "Martinez", "raulm@gmai.com", "45645645");

        Sale sale = new Sale();
        sale.setItemInstanceSet(itemInstances);
        sale.setClient(client1);
        sale.setEmployee(cajero);
        sale.setQuantityProduct(2);
        sale.setTotalAmount(new BigDecimal(45.52));
        sale.setDescription("Es una compra de mañana");
        sale.setDate(Calendar.getInstance().getTime());
        Sale sale1 = saleService.save(sale);

        assertEquals(sale1.getQuantityProduct(), 2);
        assertEquals(sale1.getTotalAmount(), new BigDecimal("45.52"));
    }

    @Test
    public void testWhenRegisterBuyWithOutItemAndShowExceptionMessage () throws  GenericException {

        assertThrows(GenericException.class, () -> {
            Set<ItemInstance> itemInstances =  new HashSet<>();
            Position gerentePosition = positionService.save(new Position("Gerente"));
            Position cajeroPosition = positionService.save(new Position("Cajero"));
            Employee cajero = createEmployee("Jose", "Vidal", "joser@gmail.com", cajeroPosition);
            createEmployee("Clara", "Soria", "csorialopez11@gmail.com", gerentePosition);
            Client client1 = createClient("Raul", "Martinez", "raulm@gmai.com", "45645645");

            ItemInstance itemInstance = new ItemInstance();
            itemInstance.setIdentifier("SKU-jeK0fkMvyzdS");
            itemInstance.setSellingPrice(new BigDecimal("45.00"));

            itemInstances.add(itemInstance);
            Sale sale = new Sale();
            sale.setItemInstanceSet(itemInstances);
            sale.setClient(client1);
            sale.setEmployee(cajero);
            sale.setQuantityProduct(2);
            sale.setTotalAmount(new BigDecimal(45.52));
            sale.setDescription("Es una compra de mañana");
            Sale sale1 = saleService.save(sale);

            assertEquals(sale1.getQuantityProduct(), 2);
            assertEquals(sale1.getTotalAmount(), new BigDecimal("45.52"));
        });
    }

    public Set<ItemInstance> createItemInstanceList() {
        Category category = new Category();
        category.setName("LIMPIEZA");
        category.setCode("LIM");
        Category category1 = categoryService.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("DETERGENTES");
        subCategory.setCode("DET-CODE");
        subCategory.setCategory(category1);
        SubCategory detSubcategori = subCategoryService.save(subCategory);

        Item item = new Item();
        item.setName("ACE PATITO");
        item.setCode("DET-PATITO");
        item.setSubCategory(detSubcategori);
        Item item1 = itemService.save(item);

        Buy compraDeItem = new Buy();
        compraDeItem.setItem(item1);
        compraDeItem.setQuantity(15);
        compraDeItem.setTotalCost(new BigDecimal(100));
        buyService.save(compraDeItem);

        Set<ItemInstance> itemInstanceList = itemInstanceService.getItemInstancesListByIdItem(item1.getId());
        Set<ItemInstance> subSet = new HashSet(new ArrayList<ItemInstance>(itemInstanceList).subList(0, 2));
        return subSet;
    }

    private Employee createEmployee(String firstName, String lastName, String email, Position position) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        Employee employee1 = employeeService.save(employee);
        createContract(employee1, position);
        return  employee1;
    }

    private Client createClient(String name, String lastName, String email, String cellphone){
        Client client = new Client();
        client.setName(name);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setCellphone(cellphone);
        client.setPoints(0);
        return clientService.save(client);
    }

    private Contract createContract(Employee employee, Position position){
        Contract contract = new Contract();
        contract.setEmployee(employee);
        contract.setPosition(position);
        return contractService.save(contract);
    }
}
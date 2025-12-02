package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierRepositoryTest {

    @Test
    void findByContactAndNameContains_shouldWork() {
        SupplierRepository repo = new SupplierRepository();

        Supplier s1 = new Supplier("ElectroCo", "support@electroco.example");
        Supplier s2 = new Supplier("HomeGoods", "contact@homegoods.example");

        repo.add(s1);
        repo.add(s2);

        List<Supplier> byName = repo.findByFirstNameContains("Electro");
        assertEquals(1, byName.size());

        List<Supplier> byContact = repo.findByContactInfoContains("homegoods");
        assertEquals(1, byContact.size());
    }
}

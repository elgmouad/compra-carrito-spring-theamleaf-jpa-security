package org.example.compracarrito.services;

import org.example.compracarrito.model.Product;
import org.example.compracarrito.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DatabaseExportServiceImpl implements DatabaseExportService{
    private final ProductRepository productRepo;

    public DatabaseExportServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public byte[] exportDatabaseToSql() {
        List<Product> products = productRepo.findAll();

        StringBuilder sql = new StringBuilder();
        sql.append("-- Exportación de productos\n");
        sql.append("DROP TABLE IF EXISTS product;\n");
        sql.append("CREATE TABLE product (\n");
        sql.append("    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n");
        sql.append("    name VARCHAR(255),\n");
        sql.append("    price DOUBLE\n");
        sql.append(");\n\n");

        for (Product p : products) {
            sql.append("INSERT INTO product (name, price) VALUES ('")
                    .append(p.getName().replace("'", "''"))
                    .append("', ")
                    .append(p.getPrice())
                    .append(");\n");
        }

        return sql.toString().getBytes(StandardCharsets.UTF_8);
    }
}

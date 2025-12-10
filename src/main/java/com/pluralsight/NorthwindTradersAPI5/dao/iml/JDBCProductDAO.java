package com.pluralsight.NorthwindTradersAPI5.dao.iml;

import com.pluralsight.NorthwindTradersAPI5.dao.Interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI5.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    @Component
    public class JDBCProductDAO implements IProductDAO {

        private final DataSource dataSource;

        @Autowired
        public JDBCProductDAO(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Override
        public List<Product> getAll() {
            List<Product> products = new ArrayList<>();
            String getAllQuery = "SELECT * FROM products";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement selectStatement = connection.prepareStatement(getAllQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    products.add(new Product(productId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return products;
        }

        @Override
        public Product getById(int productID) {
            Product product = null;
            String getByIdQuery = "SELECT * FROM products WHERE product_id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement selectStatement = connection.prepareStatement(getByIdQuery)) {

                selectStatement.setInt(1, productID);

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {

                        product = new Product(productID);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return product;

        }

        @Override
        public Product insert(Product product) {
            String insertDataQuery =
                    "INSERT INTO products (productID) VALUES (?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement insertStatement =
                         connection.prepareStatement(insertDataQuery, Statement.RETURN_GENERATED_KEYS)) {

                insertStatement.setInt(1, product.getProductID());
                insertStatement.executeUpdate();

                try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setProductID(generatedKeys.getInt(1));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return product;
        }

        @Override
        public void update(int productID, Product product) {
            String updateDataQuery =
                    "UPDATE products (productID) VALUES (?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement updateStatement = connection.prepareStatement(updateDataQuery)) {

                updateStatement.setInt(1, productID);
                updateStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void delete(int productID, Product product) {
            String deleteDataQuery =
                    "DELETE products (productID) VALUES (?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement deleteStatement = connection.prepareStatement(deleteDataQuery)) {

                deleteStatement.setInt(1, productID);
                deleteStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CartItemDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    private static final String getCartItemByUsernameString = "SELECT " +
            "I.ITEMID," +
            "LISTPRICE," +
            "UNITCOST," +
            "SUPPLIER AS supplierId," +
            "I.PRODUCTID AS \"product.productId\"," +
            "NAME AS \"product.name\"," +
            "DESCN AS \"product.description\"," +
            "category as \"product.categoryId\"," +
            "STATUS," +
            "ATTR1 AS atrribute1," +
            "ATTR2 AS attribute2," +
            "ATTR3 AS attribute3," +
            "ATTR4 AS attribute4," +
            "ATTR5 AS attribute5," +
            "QTY AS quantity ," +
            "C.ITEMID," +
            "QUANTITY, " +
            "TOTAL FROM CARTITEM C, ITEM I," +
            "INVENTORY V,PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.ITEMID = V.ITEMID AND I.ITEMID = C.ITEMID AND C.USERID = ? ";
    private static final String insertCartItemString = "INSERT INTO CARTITEM (USERID, ITEMID, QUANTITY,TOTAL) VALUES(?,?,?,?)";
    private static final String deleteCartItemByUsernameString = "DELETE FROM CARTITEM WHERE USERID = ?";

    @Override
    public List<CartItem> getCartItemsByUserId(String userId) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getCartItemByUsernameString);
            pStatement.setString(1, userId + "");
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));

                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));

                item.setProductId(product.getProductId());
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(resultSet.getInt(15));

                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(resultSet.getInt(17));

                if (item.getQuantity() > 0){
                    cartItem.setInStock(true);
                }
                else{
                    cartItem.setInStock(false);
                }
                cartItem.setTotal(resultSet.getBigDecimal(18));
                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemList;
    }

    @Override
    public void deleteCartItemByUsername(String username) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(deleteCartItemByUsernameString);
            pStatement.setString(1, username + "");
            if(pStatement.execute()){
                System.out.println("delete cartitem successfully.");
            }

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertCartItem(String username,CartItem cartItem) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(insertCartItemString);

            pStatement.setString(1,username);
            pStatement.setString(2,cartItem.getItem().getItemId());
            pStatement.setString(3,cartItem.getQuantity() +"");
            pStatement.setString(4,cartItem.getTotal() +"");
//            pStatement.execute();
            if(pStatement.execute()){
                System.out.println("insert cartitem successfully!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

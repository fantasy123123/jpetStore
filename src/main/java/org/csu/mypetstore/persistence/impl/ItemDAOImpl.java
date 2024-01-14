package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
iterator()
 */

public class ItemDAOImpl implements ItemDAO {
    private static final String getItemListByProductId = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\",DESCN AS \"product.description\",category as \"product.categoryId\",STATUS,ATTR1 AS atrribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I,PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String getItemByItemId = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\",DESCN AS \"product.description\",category as \"product.categoryId\",STATUS,ATTR1 AS atrribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity FROM ITEM I,INVENTORY V,PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.ITEMID = V.ITEMID AND I.ITEMID = ?";
    private static final String getIventoryQuantityString = "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String updateInventoryQuantityString = "UPDATE INVENTORY SET QTY = QTY-? WHERE ITEMID = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(updateInventoryQuantityString);
            String itemId = param.keySet().iterator().next();
            pStatement.setString(1,param.get("increment").toString());
            pStatement.setString(2,itemId);
            pStatement.execute();

//            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int inventory = 0;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getIventoryQuantityString);
            pStatement.setString(1,itemId);
            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next())
                inventory = resultSet.getInt(1);

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemListByProductId);
            pStatement.setString(1,productId);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
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

               itemList.add(item);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getItemByItemId);
            System.out.println(itemId);
            pStatement.setString(1,itemId);
            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()){
                item = new Item();
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

            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
}

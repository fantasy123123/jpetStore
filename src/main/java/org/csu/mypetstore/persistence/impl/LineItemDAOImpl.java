package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    private static final String getLineItemBylineItemIdString =  "SELECT lineItemID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICEFROM LINEITEM WHERE lineItemID = ?";
    private static final String insertLineItemString = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(getLineItemBylineItemIdString);
            pStatement.setString(1,orderId +"");
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemList.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(insertLineItemString);

            pStatement.setString(1,lineItem.getOrderId()+"");
            pStatement.setString(2,lineItem.getLineNumber() + "");
            pStatement.setString(3,lineItem.getItemId());
            pStatement.setString(4,lineItem.getQuantity() + "");
            pStatement.setString(5,lineItem.getUnitPrice()+"");
//            pStatement.execute();
            if(pStatement.execute()){
                System.out.println("insert lineItem successfully!");
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

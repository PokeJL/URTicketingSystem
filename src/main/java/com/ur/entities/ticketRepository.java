//Work in progress NOT TESTED.  This may or may not work.  Who knows?
//Code from https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm
package com.ur.entities;

//import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//import com.ticket.dbconnection.hibernateConnection;

public class ticketRepository implements Container {
   //public String results[] = {"Robert" , "John" ,"Julie" , "Lora"};
	//hibernateConnection conn = new hibernateConnection();
	//Session session = conn.getSession();
	//Transaction tran = conn.getTransaction(session);
	//String hql = "FROM Ticket_Details";
	//Query query = session.createQuery(hql);
	//public List results = (List) query.list();
	//conn.closeSessionwithCommit(session);
	List<ticket> tks = new ArrayList<ticket>();
	int numberOfTickets = 0;
	public List<ticket> getTickets() throws SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/cs476db?useSSL=false";

		String username = "root";
		String password = "password";

		//try {
			//Class.forName("package com.ur.entities.ticket");

			connect = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established"+connect);
		//} catch (SQLException ex) {
		//	System.out.println("in exec");
		//	System.out.println(ex.getMessage());
		//}

		//List<ticket> tks = new ArrayList<ticket>();
		PreparedStatement pstmt = connect.prepareStatement("select * Ticket_Details");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ticket tk = new ticket();
			tk.setTicketId(rs.getInt("ticketID"));
			tk.setDepartment(rs.getString("department"));
			tk.setDescription(rs.getString("description"));
			tk.setLastMod(rs.getString("lastMod"));
			tk.setLocation(rs.getString("location"));
			tk.setRoomNumber(rs.getString("roomNumber"));
			tk.setStatus(rs.getString("status"));
			tk.setTitle(rs.getString("title"));
			tk.setUser(rs.getString("user"));
			tk.setWorker(rs.getString("worker"));

			tks.add(tk);
			numberOfTickets++;
		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return tks;

	}
	
   @Override
   public Iterator getIterator() {
      return new TicketIterator();
   }

   private class TicketIterator implements Iterator {

      int index = 0;

      @Override
      public boolean hasNext() {
      
         if(index < numberOfTickets/*tks.length*/){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return tks.get(index++)/*tks[index++]*/;
         }
         return null;
      }		
   }
}

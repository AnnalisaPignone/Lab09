package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> idMap) {

		String sql = "SELECT StateAbb, ccode, StateNme FROM country ORDER BY StateAbb";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			
			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("ccode"))) {
				Country stato= new Country(rs.getString("StateAbb"), rs.getInt("ccode"), rs.getString("StateNme"));
				idMap.put(stato.getCode(), stato); 
			}
			}
			
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	
	public List<Country> getVertici(int anno, Map<Integer, Country> idMap ) {

		String sql = "SELECT state1no FROM contiguity WHERE year<=? group BY state1no";
		List <Country> countries= new ArrayList<Country>(); 
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			
			while (rs.next()) {
				Country stato= idMap.get(rs.getInt("state1no")); 
				countries.add(stato); 
			}
			
			conn.close();
			return countries; 
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore getVertici");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Border> getBorders(int anno, Map<Integer, Country> idMap ) {

		String sql = "SELECT state1no, state2no FROM contiguity WHERE year<=? AND conttype= 1 AND state1no<state2no";
		
		List <Border> borders= new ArrayList<Border>(); 
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			
			while (rs.next()) {
				Border b= new Border(idMap.get(rs.getInt("state1no")), idMap.get(rs.getInt("state2no")));
				borders.add(b); 
			}
			
			conn.close();
			return borders; 
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore getBorders");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}
}

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Indirizzo;
import model.Studente;
import persistence.dao.IndirizzoDao;

public class IndirizzoDaoJDBC implements IndirizzoDao{
	
	private DataSource dataSource;

	public IndirizzoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Indirizzo indirizzo) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Indirizzo(codice, nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1,indirizzo.getCodice());
			statement.setString(2, indirizzo.getNome());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public Indirizzo findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		Indirizzo indirizzo = null;
		try {
			PreparedStatement statement;
			String query = "select * from indirizzo where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				indirizzo = new Indirizzo();
				indirizzo.setCodice(result.getLong("codice"));				
				indirizzo.setNome(result.getString("nome"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return indirizzo;
	}

	@Override
	public List<Indirizzo> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Indirizzo> indirizzi = new LinkedList<>();
		try {
			Indirizzo indirizzo;
			PreparedStatement statement;
			String query = "select * from indirizzo";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				indirizzo = new Indirizzo();
				indirizzo.setCodice(result.getLong("codice"));				
				indirizzo.setNome(result.getString("nome"));
				indirizzi.add(indirizzo);
				
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return indirizzi;
	}

	@Override
	public void update(Indirizzo indirizzo) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update indirizzo SET codice = ?, nome = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setLong(1, indirizzo.getCodice());
			statement.setString(2, indirizzo.getNome());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public void delete(Indirizzo indirizzo) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM indirizzo WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, indirizzo.getCodice());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
		
}



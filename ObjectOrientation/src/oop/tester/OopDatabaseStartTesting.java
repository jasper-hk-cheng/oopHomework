package oop.tester;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hsqldb.jdbcDriver;

/**
 * [notice] this is the database testing class, please ignore the code below
 * when you (supervisor) code review here...
 * 
 * MyBackup MyLog
 */
public class OopDatabaseStartTesting {

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {

		try {
			Class.forName(jdbcDriver.class.getName());
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:10000/the1stOopDb");

			PreparedStatement pStmt = conn.prepareStatement("insert into MyBackup(binary_data, backup_date, backup_user) values(?, ?, 'jasper')");

			File imgFile = new File("src\\oop\\tester\\java.jpg");
			FileInputStream fileInputStream = new FileInputStream(imgFile);
			pStmt.setBinaryStream(1, fileInputStream, imgFile.length());

			Calendar now = Calendar.getInstance();
			pStmt.setTimestamp(2, new Timestamp(now.getTimeInMillis()));

			int rowCount = pStmt.executeUpdate();
			System.out.println("rowCount = " + rowCount);

			Statement selectMyBackupStmt = conn.createStatement();
			ResultSet resultSet = selectMyBackupStmt.executeQuery("select * from MyBackup");

			while (resultSet.next()) {
				int id = resultSet.getInt(1);

				Blob binaryData = resultSet.getBlob(2);
				if (binaryData != null) {
					byte[] fileByteArray = binaryData.getBytes(1, (int) binaryData.length());

					ByteArrayInputStream bais = new ByteArrayInputStream(fileByteArray);
					Path imgCopyPath = Paths.get("src\\oop\\tester\\imgCopy" + id + ".jpg");
					Files.copy(bais, imgCopyPath);
				}

				Date backupDate = resultSet.getDate(3);
				String strDate = df.format(backupDate);

				String backupUser = resultSet.getString(4);

				System.out.println("id//date//user" + id + "//" + strDate + "//" + backupUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
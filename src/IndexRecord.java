/**
 * Stefano Prezioso
 * COSC 311 Project 2
 * Fall 2014
 */

/**
 * Contains a String named "data" and an int reference to the index of the
 * record in the database.
 * 
 */
public class IndexRecord implements Comparable<IndexRecord> {
	private IndexRecord nextRecord;
	private IndexRecord previousRecord;
	private String data;
	private int databaseIndex;

	public IndexRecord(String data, int databaseIndex) {
		nextRecord = null;
		previousRecord = null;
		this.data = data.toLowerCase();
		this.databaseIndex = databaseIndex;
	}

	public String getData() {
		return data;
	}

	public int getDatabaseIndex() {
		return databaseIndex;
	}
	
	public IndexRecord getNextRecord()
	{
		return nextRecord;
	}
	
	public IndexRecord getPreviousRecord()
	{
		return previousRecord;
	}
	
	public void setNextRecord(IndexRecord recordToSet)
	{
		nextRecord = recordToSet;
	}
	
	public void setPreviousRecord(IndexRecord recordToSet)
	{
		previousRecord = recordToSet;
	}
	
	public boolean isTheEndOfTheList()
	{
		return (nextRecord == null);
	}
	
	public boolean isTheBeginningOfTheList()
	{
		return (previousRecord == null);
	}
	
	public int compareTo(IndexRecord recordToCompare)
	{
		return this.data.compareTo(recordToCompare.getData());
	}

	@Override
	public String toString() {
		return data + " " + databaseIndex;
	}

}

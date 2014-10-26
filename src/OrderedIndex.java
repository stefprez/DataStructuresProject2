/**
 * Stefano Prezioso 
 * COSC 311 Project 2 
 * Fall 2014
 */

public class OrderedIndex {
	private int numberOfRecords;
	private IndexRecord head, tail;

	public OrderedIndex() {
		head = null;
		tail = null;
		numberOfRecords = 0;
	}
	
	public IndexRecord getHead()
	{
		return head;
	}
	
	public IndexRecord getTail()
	{
		return tail;
	}
	
	public boolean isEmpty()
	{
		return (numberOfRecords == 0);
	}

	/**
	 * Returns the IndexRecord at the specified index
	 * @param indexOfRecordToReturn
	 * @return IndexRecord that was requested
	 */
	public IndexRecord getIndexRecord(int indexOfRecordToReturn) {
		IndexRecord recordToReturn = head;
		int counter = 0;
		
		while (counter < indexOfRecordToReturn)
		{
			recordToReturn = recordToReturn.getNextRecord();
			counter++;
		}
		
		return recordToReturn;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	/**
	 * Dump out the contents of the OrderedIndex to the console. For debugging
	 * purposes.
	 */
	public void printIncreasing(DataStructureRecord[] datastructure) {
		if (this.isEmpty())
		{
			System.out.println("Database is empty.");
		}
		else
		{
			IndexRecord recordToPrint = head;
			while (!recordToPrint.isTheEndOfTheList())
			{
				System.out.println(datastructure[recordToPrint.getDatabaseIndex()].toString());
				recordToPrint = recordToPrint.getNextRecord();
			}
		}
	}
	
	/**
	 * Print the DataStructure in decreasing order
	 * @param datastructure DataStructure to print
	 */
	public void printDecreasing(DataStructureRecord[] datastructure)
	{
		if (this.isEmpty())
		{
			System.out.println("Database is empty.");
		}
		else
		{
			IndexRecord recordToPrint = tail;
			while (!recordToPrint.isTheBeginningOfTheList())
			{
				System.out.println(datastructure[recordToPrint.getDatabaseIndex()]);
				recordToPrint = recordToPrint.getPreviousRecord();
			}
		}
	}

	/**
	 * Add an IndexRecord to the OrderedIndex
	 * 
	 * @param recordToAdd
	 */
	public void addRecord(IndexRecord recordToAdd) {
		
		if (this.isEmpty())
		{
			head = recordToAdd;
			tail = recordToAdd;
		}
		else if (numberOfRecords == 1)
		{
			//New record comes after single record in list
			if (head.getData().compareTo(recordToAdd.getData()) < 0)
			{
				head.setNextRecord(recordToAdd);
				recordToAdd.setPreviousRecord(head);
				tail = recordToAdd;
			}
			//New record comes before single record in list
			else
			{
				head.setPreviousRecord(recordToAdd);
				recordToAdd.setNextRecord(head);
				head = recordToAdd;
			}
				
		}
		else //List has at least two elements
		{
			//Inserting in beginning of filled list
			if (head.getData().compareTo(recordToAdd.getData()) > 0)
			{
				head.setPreviousRecord(recordToAdd);
				recordToAdd.setNextRecord(head);
				head = recordToAdd;
			}
			
			//Inserting at end of filled list
			else if(tail.getData().compareTo(recordToAdd.getData()) < 0)
			{
				tail.setNextRecord(recordToAdd);
				recordToAdd.setPreviousRecord(tail);
				tail = recordToAdd;
			}
			//Inserting in the middle of a list
			else
			{
				IndexRecord currentRecord = head.getNextRecord();
				
				while (!currentRecord.isTheEndOfTheList())
				{
					if (currentRecord.compareTo(recordToAdd) >= 0)
					{
						recordToAdd.setNextRecord(currentRecord);
						recordToAdd.setPreviousRecord(currentRecord.getPreviousRecord());
						currentRecord.getPreviousRecord().setNextRecord(recordToAdd);
						currentRecord.setPreviousRecord(recordToAdd);
						break;
					}
					else
					{
						currentRecord = currentRecord.getNextRecord();
					}
				}
			}
		}
		
		numberOfRecords++;
	}

	/**
	 * Search for and delete record in an OrderedIndex
	 * 
	 * @param databaseIndexToDelete
	 *            Unique database index to search for and delete
	 */
	public void deleteRecord(int databaseIndexToDelete) 
	{
		//Empty List
		if (numberOfRecords == 0);
		
		//One Record
		else if (numberOfRecords == 1)
		{
			if (head.getDatabaseIndex() == databaseIndexToDelete)
			{
				head = null;
				tail = null;
			}
		}
		
		else //List has at least two elements
		{
			//Record at beginning
			if (head.getDatabaseIndex() == databaseIndexToDelete)
			{
				head = head.getNextRecord();
				head.setPreviousRecord(null);
			}
			//Record at end
			else if (tail.getDatabaseIndex() == databaseIndexToDelete)
			{
				tail = tail.getPreviousRecord();
				tail.setNextRecord(null);
			}
			//Record in the middle
			else
			{
				IndexRecord currentRecord = head.getNextRecord();
				
				while (!currentRecord.isTheEndOfTheList())
				{
					if (currentRecord.getDatabaseIndex() == databaseIndexToDelete)
					{
						IndexRecord previous = currentRecord.getPreviousRecord();
						IndexRecord next = currentRecord.getNextRecord();
						
						previous.setNextRecord(next);
						next.setPreviousRecord(previous);
						currentRecord.setNextRecord(null);
						currentRecord.setPreviousRecord(null);
						break;
					}
					else
					{
						currentRecord = currentRecord.getNextRecord();
					}
				}
			}
			
		}
		
		numberOfRecords--;
	}
}

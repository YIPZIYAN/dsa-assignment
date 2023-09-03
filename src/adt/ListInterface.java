package adt;

import java.util.Iterator;

/**
 * @author Goh Chun Yen & Yip Zi Yan
 */
public interface ListInterface<T> {

    /**
     * Task: Adds a new entry to the end of the list. Entries currently in the
     * list are unaffected. The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if the list is full
     */
    public boolean add(T newEntry);

    /**
     * Task: Adds a new entry at a specified position within the list. Entries
     * originally at and above the specified position are at the next higher
     * position within the list. The list's size is increased by 1.
     *
     * @param index an integer that specifies the desired position of the new
     * entry
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if either the list
     * is full, newPosition < 1, or
     *          newPosition > getNumberOfEntries()+1
     */
    public boolean add(int index, T newEntry);

    /**
     * Task: Remove a specific entry from the list.
     *
     * @param anEntry
     * @return true if the specific entry has been removed from the list,
     * otherwise false.
     */
    public boolean remove(T anEntry);

    /**
     * Task: Removes all entries from the list.
     */
    public void clear();

    /**
     * Task: Replaces the entry at a given position in the list.
     *
     * @param index an integer that indicates the position of the entry to be
     * replaced
     * @param newEntry the object that will replace the entry at the position
     * givenPosition
     * @return true if the replacement occurs, or false if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public boolean replace(int index, T newEntry);

    /**
     * Task: Retrieves the entry at a given position in the list.
     *
     * @param index an integer that indicates the position of the desired entry
     * @return a reference to the indicated entry or null, if either the list is
     * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
     */
    public T getEntry(int index);

    /**
     * Task: Sees whether the list contains a given entry.
     *
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, or false if not
     */
    public boolean contains(T anEntry);

    /**
     * Task: Gets the number of entries in the list.
     *
     * @return the integer number of entries currently in the list
     */
    public int getNumberOfEntries();

    /**
     * Task: Sees whether the list is empty.
     *
     * @return true if the list is empty, or false if not
     */
    public boolean isEmpty();

    /**
     * Task: Get anEntry’s index in the list.
     *
     * @param anEntry
     * @return the index of anEntry in the list.
     */
    public int indexOf(T anEntry);

    /**
     * Task: Add an array of entries to the list.
     *
     * @param entries
     * @return true if the addition is successful, or false if not.
     */
    public boolean addAll(T[] entries);

    /**
     * Task: Set newEntry to the position index entry in the list.
     *
     * @param index
     * @param newEntry
     * @return true if the set is successful, or false if not.
     */
    public boolean setEntry(int index, T newEntry);

    /**
     * Task: Get the first entry in the list.
     *
     * @return the first entry in the list.
     */
    public T getFirstEntry();

    /**
     * Task: Get the last entry in the list.
     *
     * @return the last entry in the list.
     */
    public T getLastEntry();

    /**
     * Task: Get a list of entries between the position beginIndex and position endIndex.
     *
     * @param beginIndex
     * @param endIndex
     * @return the list of entries between the position beginIndex and position endIndex.
     */
    public ListInterface<T> subList(int beginIndex, int endIndex);

    public Iterator<T> getIterator();

}

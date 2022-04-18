// Project B: Linked Nodes Implementation
// 4/17/2022
// **************
// GROUPWORK WITH 
// **************
// Aohua Shen
// Hiroki Mukai
// Ensar Dedeoglu

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedFrontBackCappedList<T extends Comparable<? super T>> 
implements FrontBackCappedListInterface<T>, Comparable<LinkedFrontBackCappedList<T>> {

	private Node head, tail;
	private int numberOfEntries;
	int setCapacity = 0;

	public LinkedFrontBackCappedList(int capacity){
		head = null;
		tail = null;
		setCapacity = capacity;
		numberOfEntries = 0;
	}

	@Override

	public int compareTo(LinkedFrontBackCappedList<T> other) {
		if (this.isEmpty() && other.isEmpty()) {
			return 0; 
		}
		if (this.numberOfEntries == other.numberOfEntries){
			for (int i = 0; i < numberOfEntries; i++) {
				if (this.getEntry(i).compareTo(other.getEntry(i)) > 0) {
					return 1;
				} else if (this.getEntry(i).compareTo(other.getEntry(i)) < 0) {
					return -1;
				}
			}
			return 0;
		} else if (this.numberOfEntries > other.numberOfEntries) {
			for (int i = 0; i < other.numberOfEntries; i++) {
				if (this.getEntry(i).compareTo(other.getEntry(i)) > 0) {
					return 1;
				} else if (this.getEntry(i).compareTo(other.getEntry(i)) < 0) {
					return -1;
				}
			}
			return 1;
		} else {
			for (int i = 0; i < numberOfEntries; i++) {
				if (this.getEntry(i).compareTo(other.getEntry(i)) > 0) {
					return 1;
				} else if (this.getEntry(i).compareTo(other.getEntry(i)) < 0) {
					return -1;
				}
			}
			return -1;
		}
	}

	@Override
	public String toString(){
		if(isEmpty()){
			return "[]" + "\t" + "size=" + numberOfEntries + " capacity=" + setCapacity;
		}else {
			T[] result = (T[]) new Comparable[numberOfEntries];
			Node currentNode = head;
			for (int i = 0; i < numberOfEntries; i++){
				result[i] = currentNode.data;
				currentNode = currentNode.next;
			}
			if (numberOfEntries == 1){
				return Arrays.toString(result) + "\t" + "size=" + numberOfEntries + " capacity=" + setCapacity + " head=" + head.data + " tail=" + tail.data;

			}else {
				return Arrays.toString(result) + "\t" + "size=" + numberOfEntries + " capacity=" + setCapacity + " head=" + head.data + " tail=" + tail.data;
			}
		}
	}

	public boolean isEmpty(){
		if(numberOfEntries == 0){
			return true;
		}else {
			return false;
		}
	}

	public boolean isFull(){
		if (numberOfEntries == setCapacity){
			return true;
		}else {
			return false;
		}
	}

	public int size(){
		return numberOfEntries;
	}


	public T getEntry(int givenPosition){
		if (validPosition(givenPosition)){
			return getNodeAt(givenPosition).data;
		}else {
			return null;
		}
	}

	public void clear(){
		head = null;
		tail = null;
		numberOfEntries = 0;
	}

	public boolean addFront(T newEntry){
		if (isFull()){
			return false;
		}else {
			if(isEmpty()){
				Node newNode = new Node(newEntry);
				head = newNode;
				tail= newNode;
				numberOfEntries++;
				return true;
			}else{
				Node newNode = new Node(newEntry);
				newNode.next = head;
				head = newNode;
				numberOfEntries++;
				return true;
			}
		}
	}

	public boolean addBack(T newEntry) {
		if (isFull()) {
			return false;
		} else {
			if (isEmpty()) {
				Node newNode = new Node(newEntry);
				head = newNode;
				tail= newNode;
				numberOfEntries++;
				return true;
			}else {
				Node newNode = new Node(newEntry);
				tail.setNextNode(newNode);
				tail = tail.next;
				numberOfEntries++;
				return true;
			}
		}
	}

	public T removeFront(){
		if (isEmpty()){
			return null;
		}else if (numberOfEntries == 1) {
			T result = head.data;
			head = null;
			tail = null;
			numberOfEntries--;
			return result;
		}else {
			T result = head.data;
			head = head.next;
			numberOfEntries--;
			return result;
		}
	}

	public T removeBack(){
		if (isEmpty()){
			return null;
		}else if (numberOfEntries == 1){
				T result = head.data;
				head = null;
				tail = null;
				numberOfEntries--;
				return result;
		}else {
			T result = tail.data;
			tail = getNodeAt(numberOfEntries - 2);
			numberOfEntries--;
			tail.next = null;
			return result;
		}
	}

	public boolean contains (T anEntry){
		boolean found = false;
		Node currentNode = head;
		while (!found && (currentNode != null)){
			if (anEntry.equals(currentNode.data)){
				found = true;
			}else {
				currentNode = currentNode.next;
			}
		}
		return found;
	}

	public int indexOf(T anEntry){
		int position = -1;
		if(isEmpty()){
			return position;
		}
		else if (numberOfEntries == 1) {
			if(head.data.equals(anEntry)){
				return 0;
			}else {
				return position;
			}
		}
		else {
			Node currentNode = head;
			for (int i = 0; i < numberOfEntries; i ++){
				if(currentNode.data.equals(anEntry)){
					return i;
				}
				currentNode = currentNode.next;
			}
			return position;
		}
	}

	public  int lastIndexOf(T anEntry){
		int position = -1;
		if(isEmpty()){
			return position;
		}
		else if (numberOfEntries == 1) {
			if(head.data.equals(anEntry)){
				return 0;
			}else {
				return position;
			}
		}
		else {
			Node currentNode = head;
			List<Integer> positionNote = new ArrayList<>();
			for (int i = 0; i < numberOfEntries; i ++){
				if(currentNode.data.equals(anEntry)){
					positionNote.add(i);
				}
				currentNode = currentNode.next;
			}
			if (positionNote.isEmpty()){
				return -1;
			}else {
				return positionNote.get(positionNote.size()-1);
			}
		}
	}

	private boolean validPosition(int position){
		return position >= 0 && position <= numberOfEntries-1;
	}

	private Node getNodeAt(int givenPosition){
		Node currentNode = head;
		for(int counter = 0; counter < givenPosition; counter++){
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	

	// YOUR CLASS CODE GOES HERE!

	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	} 
}

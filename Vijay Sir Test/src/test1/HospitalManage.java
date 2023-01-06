package test1;

import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManage {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		
		HospitalManage hm = new HospitalManage();
		// Hospital Setup 
		Clinic clinic1 = hm.setupClinic();

		// Assigning doctor for a patient
		Doctor doc = hm.getDoctor(clinic1);


		// Getting Medicine List & adding to Prescription
		ArrayList<Medicine> medList = hm.getMedList( clinic1);
		

		//		Printing Prescription
		Prescription pres = new Prescription(doc, medList);
		if (medList.size() != 0) {
			pres.printPrescription();
		}

	}

	private ArrayList<Medicine> getMedList( Clinic clinic1) {
		ArrayList<Medicine> medList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		int medicineNum = 0;
		do {
			clinic1.listPharmacy();
			System.out.println("Enter the MedID or Enter 0 to complete");
			medicineNum = scan.nextInt();
			if (medicineNum == 0) {
				break;
			}
			Medicine med = clinic1.getMedbyID(medicineNum);
			while (med == null) {
				System.out.println("invalid MedID - Re-enter or Enter 0 to complete");
				medicineNum = scan.nextInt();
				if (medicineNum == 0) {
					break;
				}
				med = clinic1.getMedbyID(medicineNum);
			}
			Medicine medi = new Medicine(med);
			System.out.println("Please enter the quantity");
			int count = scan.nextInt();
			while (med.getQuantityAvailable() < count) {
				System.out
				.println("Please enter lesser quantity\nOnly " + med.getQuantityAvailable() + " nos available");
				count = scan.nextInt();
			}
			med.setQuantityAvailable(med.getQuantityAvailable() - count);
			medi.setQuantityAvailable(count);
			medi.setCostPerUnit(medi.getCostPerUnit() * count);
			medList.add(medi);
		} while (medicineNum != 0);
		
		return medList;


	}

	private Doctor getDoctor(Clinic clinic1) {
		// TODO Auto-generated method stub
		System.out.println("Please Enter the Doctor's number you want to visit\n");
		Scanner scan = new Scanner(System.in);
		clinic1.listDoctors();
		int docNum = scan.nextInt();
		Doctor doc = clinic1.getDocbyID(docNum);

		while (doc == null) {
			System.out.println("invalid DocNum - Re-enter ");
			docNum = scan.nextInt();

			doc = clinic1.getDocbyID(docNum);
		}
		return doc;
	}

	private Clinic setupClinic() {
		Doctor d1 = new Doctor(1, "Vignesh");
		Doctor d2 = new Doctor(2, "Gokul");
		Doctor d3 = new Doctor(3, "Amudhan");
		Doctor d4 = new Doctor(4, "Rajesh");
		Doctor d5 = new Doctor(5, "Surya");
		Doctor d6 = new Doctor(6, "Amuthan");
		Doctor d7 = new Doctor(7, "Sneha");
		Doctor d8 = new Doctor(8, "Nibila");
		Doctor d9 = new Doctor(9, "Vijayan");
		Doctor d10 = new Doctor(10, "Jagadeesh");
		ArrayList<Doctor> docList = new ArrayList<>();
		docList.add(d1);
		docList.add(d2);
		docList.add(d3);
		docList.add(d4);
		docList.add(d5);
		docList.add(d6);
		docList.add(d7);
		docList.add(d8);
		docList.add(d9);
		docList.add(d10);

		Medicine m1 = new Medicine(1, "Para", 1250, 2);
		Medicine m2 = new Medicine(2, "Amox", 1000, 20);
		Medicine m3 = new Medicine(3, "digine", 12500, 12);
		Medicine m4 = new Medicine(4, "1tab4", 125, 25);
		Medicine m5 = new Medicine(5, "1tab5", 100, 200);

		ArrayList<Medicine> mediList1 = new ArrayList<>();
		mediList1.add(m5);
		mediList1.add(m4);
		mediList1.add(m3);
		mediList1.add(m2);
		mediList1.add(m1);

		Pharmacy p1 = new Pharmacy(mediList1, "Pharmacy1", 1);

		Medicine m11 = new Medicine(11, "2Tab1", 1250, 2);
		Medicine m12 = new Medicine(12, "2Tab2", 1000, 20);
		Medicine m13 = new Medicine(13, "2Tab3", 12500, 12);
		Medicine m14 = new Medicine(14, "2Tab4", 125, 25);
		Medicine m15 = new Medicine(15, "2Tab5", 100, 200);

		ArrayList<Medicine> mediList2 = new ArrayList<>();
		mediList2.add(m15);
		mediList2.add(m14);
		mediList2.add(m13);
		mediList2.add(m12);
		mediList2.add(m11);

		Pharmacy p2 = new Pharmacy(mediList2, "Pharmacy2", 2);

		ArrayList<Pharmacy> pharmacyList = new ArrayList<>();
		pharmacyList.add(p1);
		pharmacyList.add(p2);

		Clinic clinic = new Clinic(docList, pharmacyList);
		return clinic;
	}

}

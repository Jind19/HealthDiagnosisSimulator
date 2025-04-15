package HealthDiagnosis;

import java.util.ArrayList;
import java.util.Scanner;

public class HealthDiagnosisSimulator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Disease[] diseases = {
                new Disease("Common Cold", 1),
                new Disease("Seasonal Cold", 1),
                new Disease("Bronchitis", 2),
                new Disease("Pneumonia", 2),
                new Disease("COVID-19", 2),
                new Disease("Tuberculosis", 3),
                new Disease("Lung Cancer", 3)
        };

        //Ask for the patient's symptom severity
        System.out.println("Welcome to the Health diagnoses Simulator");
        System.out.println("On a scale of 1(Mild) to 3(Severe), how severe are the symptoms");

        int patientSeverity = scanner.nextInt();

        //Use binary search to find a matching disease
        ArrayList<Disease> diagnoses = findDiseaseBySeverity(diseases,patientSeverity);



        if(diagnoses.isEmpty() ){
            System.out.println("No matching diagnoses found for that severity.");

        } else {
            System.out.println("Possible diagnoses: ");
            for (Disease d : diagnoses) {
                System.out.println(" - " + d.name);
            }
        }
        scanner.close();
    }

    /**
     * @description Binary search find *one* match, then expand left and right
     * to find *all* disease with the same severity.
     * @param diseases : ascending sorted array by severity
     * @param patientSeverity : the symptoms severity level
     * @return ArrayList<Disease> : All Diseases with the same severity.
     * */

    static ArrayList<Disease> findDiseaseBySeverity(Disease[] diseases, int patientSeverity) {
        ArrayList<Disease> result = new ArrayList<>();

        int low = 0;
        int high = diseases.length - 1;
        int foundIndex = -1;  //-1 means no match

        // Standard Binary search to find one match
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Checking for: " + diseases[mid].name + " (Severity: " + diseases[mid].severity + ")");

            if (diseases[mid].severity == patientSeverity) {
                foundIndex = mid;
                break;      //One match found

            } else if (diseases[mid].severity < patientSeverity) {
                low = mid + 1; //go right
            } else {
                high = mid - 1; //go left
            }
        }
        //if no match found, return empty list
        if(foundIndex == -1) {
            return result;
        }
        //Explore left and right from the found index
        int left = foundIndex;
        int right = foundIndex + 1;

        //Move left to find other matches
        while(left >= 0 && diseases[left].severity == patientSeverity){
            result.add(diseases[left]);
            left--;
        }

        //Move right to find other matches
        while(right < diseases.length && diseases[right].severity == patientSeverity){
            result.add(diseases[right]);
            right++;
        }

        return result; //no match
    }

}

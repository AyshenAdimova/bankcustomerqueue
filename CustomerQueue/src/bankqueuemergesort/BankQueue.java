package bankqueuemergesort;
import java.util.Arrays;
import java.util.Scanner;

class Customer {
    int id;
    String name;
    int priority;
    String type; 

    public Customer(int id, String name, int priority, String type) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Priority: " + priority + ", Type: " + type;
    }
}

public class BankQueue {
    
    public static void mergeSort(Customer[] customers, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(customers, left, mid);
            mergeSort(customers, mid + 1, right);
            merge(customers, left, mid, right);
        }
    }

    public static void merge(Customer[] customers, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Customer[] leftArray = new Customer[n1];
        Customer[] rightArray = new Customer[n2];
        
        for (int i = 0; i < n1; i++)
            leftArray[i] = customers[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = customers[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].type.equals("VIP") && rightArray[j].type.equals("Sadə")) {
                customers[k] = leftArray[i];
                i++;
            } else if (leftArray[i].type.equals("Sadə") && rightArray[j].type.equals("VIP")) {
                customers[k] = rightArray[j];
                j++;
            } else if (leftArray[i].priority <= rightArray[j].priority) {
                customers[k] = leftArray[i];
                i++;
            } else {
                customers[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            customers[k] = leftArray[i];
            i++; k++;
        }
        while (j < n2) {
            customers[k] = rightArray[j];
            j++; k++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Neçə müştəri daxil etmək istəyirsiniz? ");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        Customer[] customers = new Customer[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Müştərinin ID-si: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Müştərinin adı: ");
            String name = scanner.nextLine();
            System.out.print("Müştərinin prioriteti (1-10): ");
            int priority = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Müştərinin növü (VIP/Sadə): ");
            String type = scanner.nextLine();
            customers[i] = new Customer(id, name, priority, type);
        }
        
        mergeSort(customers, 0, customers.length - 1);
        
        System.out.println("\nSıralanmış müştəri növbəsi:");
        Arrays.stream(customers).forEach(System.out::println);
        
        scanner.close();
    }
}
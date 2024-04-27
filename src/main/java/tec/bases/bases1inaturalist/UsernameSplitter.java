package tec.bases.bases1inaturalist;

public class UsernameSplitter {

    public static String[] splitUsername(String username) {
        // Split the username using spaces
        String[] parts = username.split("\\s+");

        String[] result = new String[3];

        if (parts.length >= 3) {
            // First name is the first part
            result[0] = parts[0];
            // Last name is the last part
            result[2] = parts[parts.length - 1];

            // Construct second last name by joining parts in between
            StringBuilder secondLastNameBuilder = new StringBuilder();
            for (int i = 1; i < parts.length - 1; i++) {
                secondLastNameBuilder.append(parts[i]);
                if (i < parts.length - 2) {
                    secondLastNameBuilder.append(" ");
                }
            }
            result[1] = secondLastNameBuilder.toString();
        } else if (parts.length == 2) {
            // If only two parts, consider them as first name and last name
            result[0] = parts[0];
            result[2] = parts[1];
            result[1] = ""; // Second last name is empty
        } else if (parts.length == 1) {
            // If only one part, consider it as first name
            result[0] = parts[0];
            result[1] = ""; // Both last name and second last name are empty
            result[2] = "";
        } else {
            // If username is empty, return empty strings
            result[0] = "";
            result[1] = "";
            result[2] = "";
        }

        return result;
    }

    public static void main(String[] args) {
        String username = "John Doe Smith";
        String[] parts = splitUsername(username);

        System.out.println("First Name: " + parts[0]);
        System.out.println("Last Name: " + parts[2]);
        System.out.println("Second Last Name: " + parts[1]);
    }
}

package backend.utilities;

public class ValidationHelper {

        public static boolean isValidCCCD(String cccd) {
            return cccd.matches("\\d{12}"); // CCCD phải là 12 chữ số
        }
    }



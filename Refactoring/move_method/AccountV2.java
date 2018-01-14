class Account {
    double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0) result += _type.overdraftCharge(_daysOverdrawn);
        return result;
    }

    private AccountType _type;
    private int _daysOverdrawn;
}

class AccountType {
    double overdraftCharge(Account account) {
        if (isPremium()) {
            double result += 10;
            if (account.getDaysOverdrawn() > 7) {
                result += (account.getDaysOverdrawn() - 7) * 0.85;
            }
            return result;
        } else {
            return account.getDaysOverdrawn() * 1.75;
        }
    }
}

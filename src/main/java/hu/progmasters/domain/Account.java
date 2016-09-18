package hu.progmasters.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Size(min = 4, max = 30)
    String userName;

    String ipAddress;

    @NotNull
    @Size(min = 5, max = 100)
    String goalName;

    Integer balance;

    Integer funds;

    @OneToMany(mappedBy = "from")
    private List<Transfer> targetTransfers;

    @OneToMany(mappedBy = "to")
    private List<Transfer> sourceTransfers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public List<Transfer> getTargetTransfers() {
        return targetTransfers;
    }

    public void setTargetTransfers(List<Transfer> targetTransfers) {
        this.targetTransfers = targetTransfers;
    }

    public List<Transfer> getSourceTransfers() {
        return sourceTransfers;
    }

    public void setSourceTransfers(List<Transfer> sourceTransfers) {
        this.sourceTransfers = sourceTransfers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (getId() != null ? !getId().equals(account.getId()) : account.getId() != null) return false;
        if (getUserName() != null ? !getUserName().equals(account.getUserName()) : account.getUserName() != null)
            return false;
        if (getIpAddress() != null ? !getIpAddress().equals(account.getIpAddress()) : account.getIpAddress() != null)
            return false;
        return !(getGoalName() != null ? !getGoalName().equals(account.getGoalName()) : account.getGoalName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getIpAddress() != null ? getIpAddress().hashCode() : 0);
        result = 31 * result + (getGoalName() != null ? getGoalName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return goalName + "(" + userName + ")" + " - balance: " + balance + ", funds: " + funds;
    }
}

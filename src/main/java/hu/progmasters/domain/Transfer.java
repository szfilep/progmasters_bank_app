package hu.progmasters.domain;

import hu.progmasters.validation.TransferAmount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Transfer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transfer_from", nullable = false)
    @NotNull(message = "{transfer.from.notnull}")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "transfer_to", nullable = false)
    @NotNull(message = "{transfer.to.notnull}")
    private Account to;

    @TransferAmount
    private Integer amount = 0;

    private Date timeStamp;

    public Transfer() {
    }

    public Transfer(Account from) {
        this.from = from;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;

        Transfer transfer = (Transfer) o;

        if (getId() != null ? !getId().equals(transfer.getId()) : transfer.getId() != null) return false;
        if (getFrom() != null ? !getFrom().equals(transfer.getFrom()) : transfer.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(transfer.getTo()) : transfer.getTo() != null) return false;
        if (getAmount() != null ? !getAmount().equals(transfer.getAmount()) : transfer.getAmount() != null)
            return false;
        return !(getTimeStamp() != null ? !getTimeStamp().equals(transfer.getTimeStamp()) : transfer.getTimeStamp() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getTimeStamp() != null ? getTimeStamp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        //return "Transfer from " + from.getUserName() + " to " + to.getGoalName() + " with amount " + amount + " at " + timeStamp;
        return "'" + from.getUserName() + "' => '" + to.getGoalName() + "', összeg: " + amount + ", időpont: " + timeStamp + ";";
    }
}

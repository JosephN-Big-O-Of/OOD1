import java.util.Calendar;
import java.util.Date;

public class Subscription implements Subscribable {
    private String plan;
    private boolean active;
    private Date expiryDate;

    //we can remove notigfyUser BUT it forces us to make it abstract, so we cant instantiate later, we will develop it more in XML and such
    

    @Override
    public void subscribe(String plan) {
        if (plan == null || plan.trim().isEmpty()) {
            throw new InvalidMediaException("Subscription plan cannot be null/empty.");
        }

        this.plan = plan.trim();
        this.active = true;

        // 30 days expires from now
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.expiryDate = cal.getTime();

        System.out.println("Subscribed to " + this.plan + " until " + this.expiryDate);
    }

    @Override
    public void cancel() {
        active = false;
        System.out.println("Subscription canceled.");
    }

    @Override
    public boolean isActive() {
        
        if (!active) return false;
        if (expiryDate == null) return false;
        return new Date().before(expiryDate);
    }
//this is so that expiration is taken into accnt.
    public String getPlan() {
        return plan;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void notifyUser() {
     if (!active) {
            System.out.println("[Subscription] Inactive (canceled).");
            return;
        }

        if (expiryDate == null) {
            System.out.println("[Subscription] Active. (No expiry date set)");
            return;
        }

        System.out.println("[Subscription] Active plan '" + plan + "' until " + expiryDate);
    }
}
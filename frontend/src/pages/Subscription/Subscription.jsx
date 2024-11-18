import { useSelector } from "react-redux";
import SubscriptionCard from "./SubscriptionCard";

const freePlan = [
  "Add Only 3 Projects",
  "Basic Task Management",
  "Project Collaboration",
  "Basic Reporting",
  "Email Notifications",
  "Basic Acess Control",
];

const paidPlan = [
  "Add Unlimited Projects",
  "Access Live Chat",
  "Add Unlimited Team Members",
  "Advanced Reporting",
  "Priority Support",
  "Customization Options",
  "Integration Support",
  "Advanced Security",
  "Training and Resource",
  "Access Control",
  "Custome Workflows",
];

const annualPlan = [
  "Add Unlimited Projects",
  "Access Live Chat",
  "Add Unlimited Team Members",
  "Advanced Reporting",
  "Priority Support",
  "Everything Monthly Support",
];

const Subscription = () => {
  const userSubscription = useSelector(
    (state) => state.subscription.userSubscription
  );
  return (
    <div className="p-10">
      <h1 className="text-5xl font-semibold py-5 pb-16 text-center">Pricing</h1>
      <div className="flex flex-col lg:flex-row justify-center items-center gap-9">
        <SubscriptionCard
          data={{
            planName: "Free",
            features: freePlan,
            planType: "FREE",
            price: 0,
            buttonName:
              userSubscription?.planType == "FREE"
                ? "Current Plan"
                : "Get Started",
          }}
        />
        <SubscriptionCard
          data={{
            planName: "Monthly Paid Plan",
            features: paidPlan,
            planType: "MONTHLY",
            price: 99,
            buttonName:
              userSubscription?.planType == "MONTHLY"
                ? "Current Plan"
                : "Get Started",
          }}
        />

        <SubscriptionCard
          data={{
            planName: "Annually Paid Plan",
            features: annualPlan,
            planType: "ANNUALLY",
            price: 1000,
            buttonName:
              userSubscription?.planType == "ANNUALLY"
                ? "Current Plan"
                : "Get Started",
          }}
        />
      </div>
    </div>
  );
};

export default Subscription;

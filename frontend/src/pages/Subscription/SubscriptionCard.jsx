import { Button } from "@/components/ui/button";
import { upgradeSubscription } from "@/Redux/Subscription/Action";
import { CheckCircledIcon } from "@radix-ui/react-icons";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router";

const SubscriptionCard = ({ data }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleUpgrade = async () => {
    await dispatch(upgradeSubscription({ planType: data.planType }));
    navigate("/upgrade_plan/success");
  };
  return (
    <div className="rounded-xl bg-[#1b1b1b] bg-opacity-20 shadow-[#14173b] shadow-2xl card p-5 space-y-5 w-[18rem]">
      <p>{data.planName}</p>
      <p>
        <span className="text-xl font-semibold">${data.price}</span>
        <span>/{data.planType}</span>
      </p>
      {data.planType == "ANNUALLY" && (
        <p className="text-green-500">16.67% off</p>
      )}

      <Button onClick={handleUpgrade} className="w-full">
        {data.buttonName}
      </Button>

      <div>
        {data.features.map((item) => (
          <div key={item} className="flex items-center gap-3">
            <CheckCircledIcon />
            <p>{item}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SubscriptionCard;

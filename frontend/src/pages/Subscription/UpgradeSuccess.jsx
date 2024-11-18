import { Button } from "@/components/ui/button";
import { Card } from "@/components/ui/card";
import { CheckCircledIcon } from "@radix-ui/react-icons";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";

const UpgradeSuccess = () => {
  const navigate = useNavigate();
  const userSubscription = useSelector(
    (state) => state.subscription.userSubscription
  );

  console.log("Current Plan in Redux: ", userSubscription);

  return (
    <div className="flex justify-center">
      <Card className="mt-20 p-5 space-y-5 flex flex-col items-center">
        <div className="flex item-center gap-4">
          <CheckCircledIcon className="w-9 h-9 text-green-500" />
          <p className="text-xl">Plan Upgraded Successfully!</p>
        </div>

        <div className="space-y-3">
          <p className="text-green-500">
            Start Date: {userSubscription?.startDate}
          </p>
          <p className="text-red-500">End Date: {userSubscription?.endDate}</p>
          <p className="">Plan Type: {userSubscription?.planType}</p>
        </div>
        <Button onClick={() => navigate("/")}>Go To Home</Button>
      </Card>
    </div>
  );
};

export default UpgradeSuccess;

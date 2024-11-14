import { Button } from "@/components/ui/button";
import { CheckCircledIcon } from "@radix-ui/react-icons";

const SubscriptionCard = ({ data }) => {
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

      <Button className="w-full">{data.buttonName}</Button>

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

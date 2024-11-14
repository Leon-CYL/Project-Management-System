import { Avatar, AvatarFallback } from "@/components/ui/avatar";

const UserList = () => {
  return (
    <>
      <div className="space-y-2">
        <div className="rounded-md border">
          <p className="py-2 px-3">{"Leon" || "unassignee"}</p>
        </div>
        <div className="py-2 group hover:bg-slate-800 flex cursor-pointer items-center space-x-4 rounded-md border px-4">
          <Avatar>
            <AvatarFallback>L</AvatarFallback>
          </Avatar>
          <div className="space-y-1">
            <p className="text-sm leading-none">@Leon</p>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserList;

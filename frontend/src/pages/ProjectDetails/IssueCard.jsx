import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { DotsVerticalIcon, PersonIcon } from "@radix-ui/react-icons";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import UserList from "./UserList";
import { useNavigate } from "react-router";
import { useDispatch } from "react-redux";
import { deleteIssue } from "@/Redux/Issue/Action";

const IssueCard = ({ item, projectId }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleIssueDelete = () => {
    dispatch(deleteIssue(item.id));
  };
  return (
    <Card className="rounded-md py-2">
      <CardHeader className="py-0 pb-1">
        <div className="flex justify-between items-center">
          <CardTitle
            onClick={() => navigate(`/project/${projectId}/issue/${item.id}`)}
            className="cursor-pointer"
          >
            {item.title}
          </CardTitle>
          <DropdownMenu>
            <DropdownMenuTrigger>
              <Button className="rounded-full" size="icon" variant="ghost">
                <DotsVerticalIcon />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuItem>In Progress</DropdownMenuItem>
              <DropdownMenuItem>Done</DropdownMenuItem>
              <DropdownMenuItem>Edit</DropdownMenuItem>
              <DropdownMenuItem onClick={handleIssueDelete}>
                Delete
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </CardHeader>
      <CardContent className="py-0">
        <div className="flex items-center justify-between">
          <p>FBP - {1}</p>
          <DropdownMenu className="2-[30rem] border border-red-400">
            <DropdownMenuTrigger>
              <Button
                size="icon"
                className="bg-gray-900 hover:text-black text-white rounded-full"
              >
                <Avatar>
                  <AvatarFallback>
                    <PersonIcon />
                  </AvatarFallback>
                </Avatar>
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <UserList />
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </CardContent>
    </Card>
  );
};

export default IssueCard;

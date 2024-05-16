import { Dispatch, Fragment, SetStateAction, useState } from "react";
import {
  Dialog,
  DialogPanel,
  Transition,
  TransitionChild,
} from "@headlessui/react";
import {
  XMarkIcon,
} from "@heroicons/react/24/outline";
import PowerSettingsNewIcon from "@mui/icons-material/PowerSettingsNew";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import NotificationsIcon from '@mui/icons-material/Notifications';
import GroupIcon from '@mui/icons-material/Group';
import EditIcon from '@mui/icons-material/Edit';
import Diversity3Icon from '@mui/icons-material/Diversity3';
import FavoriteIcon from '@mui/icons-material/Favorite';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from "react-router-dom";
import Logo from "../../assets/logo.png";

interface navTypes {
  name: string;
  to: string;
  icon: any;
}
const navigation: navTypes[] = [
  { name: "Profile", to: "profile", icon: AccountCircleIcon },
  { name: "Edit Profile", to: "editProfile", icon: EditIcon },
  { name: "Notifications", to: "notifications", icon: NotificationsIcon },
  { name: "Following", to: "following", icon: GroupIcon },
  { name: "Followers", to: "followers", icon: Diversity3Icon },
  { name: "Likes", to: "likes", icon: FavoriteIcon },
  { name: "Comments", to: "comments", icon: CommentIcon },
  { name: "WishList", to: "wishList", icon: BookmarkIcon },
];

function classNames(...classes: (string | undefined | null | false)[]): string {
  return classes.filter(Boolean).join(" ");
}

interface DashSideNavPropType {
  sidebarOpen: boolean;
  setSidebarOpen: Dispatch<SetStateAction<boolean>>;
}

function DashBoardSideNav({
  sidebarOpen,
  setSidebarOpen,
}: DashSideNavPropType) {
  const [currentIndex, setCurrentIndex] = useState<number>(0);
  return (
    <>
      <Transition show={sidebarOpen} as={Fragment}>
        <Dialog
          as="div"
          className="relative z-40 md:hidden"
          onClose={setSidebarOpen}
        >
          <TransitionChild
            as={Fragment}
            enter="transition-opacity ease-linear duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="transition-opacity ease-linear duration-300"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-[#2e2d2d] bg-opacity-75" />
          </TransitionChild>

          <div className="fixed inset-0 z-40 flex">
            <TransitionChild
              as={Fragment}
              enter="transition ease-in-out duration-300 transform"
              enterFrom="-translate-x-full"
              enterTo="translate-x-0"
              leave="transition ease-in-out duration-300 transform"
              leaveFrom="translate-x-0"
              leaveTo="-translate-x-full"
            >
              <DialogPanel className="relative flex w-full max-w-xs flex-1 flex-col bg-primary">
                <TransitionChild
                  as={Fragment}
                  enter="ease-in-out duration-300"
                  enterFrom="opacity-0"
                  enterTo="opacity-100"
                  leave="ease-in-out duration-300"
                  leaveFrom="opacity-100"
                  leaveTo="opacity-0"
                >
                  <div className="absolute top-0 right-0 -mr-12 pt-2">
                    <button
                      type="button"
                      className="ml-1 flex h-10 w-10 items-center justify-center rounded-full focus:outline-none focus:ring-2 focus:ring-inset focus:ring-[#FCDD1F]"
                      onClick={() => setSidebarOpen(false)}
                    >
                      <span className="sr-only">Close sidebar</span>
                      <XMarkIcon
                        className="h-6 w-6 text-fourth"
                        aria-hidden="true"
                      />
                    </button>
                  </div>
                </TransitionChild>
                <div className="h-0 flex-1 overflow-y-auto pt-5 pb-4">
                  <div className="flex flex-shrink-0 items-center w-[100%] justify-center px-4">
                    <img src={Logo} className="h-4 w-auto" />
                  </div>
                  <nav className="mt-5 flex-1 flex flex-col items-center justify-center h-[93%] space-y-1 px-2">
                    {navigation.map((item, index) => (
                      <Link
                        to={item.to}
                        key={item.name}
                        onClick={() => setCurrentIndex(index)}
                        className={classNames(
                          index === currentIndex
                            ? "bg-fifth text-[#F6F6F6]"
                            : "text-gray-300 hover:bg-[#af9f33] hover:text-white",
                          "group flex items-center px-2 py-2 w-[80%] text-base font-medium rounded-md"
                        )}
                      >
                        <item.icon
                          className={classNames(
                            index === currentIndex
                              ? "text-fourth"
                              : "text-fifth",
                            "mr-4 flex-shrink-0 h-6 w-6"
                          )}
                          aria-hidden="true"
                        />
                        {item.name}
                      </Link>
                    ))}
                  </nav>
                </div>
                <div className="flex flex-shrink-0 items-center w-[100%] bg-primary p-4 justify-center px-2">
                  <Link
                    to={""}
                    key={"logout"}
                    onClick={() => setCurrentIndex(-1)}
                    className={classNames(
                      -1 === currentIndex
                        ? "bg-fifth text-[#F6F6F6]"
                        : "text-gray-300 hover:bg-[#af9f33] hover:text-white",
                      "group flex items-center px-2 w-[90%] py-2 text-sm sm:text-lg lg:text-xl font-medium rounded-md"
                    )}
                  >
                    <PowerSettingsNewIcon
                      className={classNames(
                        -1 === currentIndex ? "text-fourth" : "text-fifth",
                        "mr-4 flex-shrink-0 text-sm sm:text-lg lg:text-xl h-6 w-6"
                      )}
                      aria-hidden="true"
                    />
                    {"log out"}
                  </Link>
                </div>
              </DialogPanel>
            </TransitionChild>
            <div className="w-14 flex-shrink-0"></div>
          </div>
        </Dialog>
      </Transition>
      <div className="hidden md:fixed md:inset-y-0 md:flex md:w-64 md:flex-col">
        <div className=" flex min-h-0 flex-1 flex-col bg-primary">
          <div className="flex flex-1 flex-col overflow-y-auto pt-5 pb-4">
            <div className="flex flex-shrink-0 w-[100%] justify-center items-center px-4">
              <img className="h-4 w-auto" src={Logo} alt="Your Company" />
            </div>
            <nav className="mt-5 flex-1 flex flex-col items-center justify-center space-y-3 px-2">
              {navigation.map((item, index) => (
                <Link
                  to={item.to}
                  key={item.name}
                  onClick={() => setCurrentIndex(index)}
                  className={classNames(
                    index === currentIndex
                      ? "bg-fifth text-[#F6F6F6]"
                      : "text-gray-300 hover:bg-[#af9f33] hover:text-white",
                    "group flex items-center px-2 w-[90%] py-2 text-sm sm:text-lg lg:text-xl font-medium rounded-md"
                  )}
                >
                  <item.icon
                    className={classNames(
                      index === currentIndex ? "text-fourth" : "text-fifth",
                      "mr-4 flex-shrink-0 text-sm sm:text-lg lg:text-xl h-6 w-6"
                    )}
                    aria-hidden="true"
                  />
                  {item.name}
                </Link>
              ))}
            </nav>
          </div>
          <div className="flex flex-shrink-0 items-center w-[100%] bg-primary p-4 justify-center px-2">
            <Link
              to={""}
              key={"logout"}
              onClick={() => setCurrentIndex(-1)}
              className={classNames(
                -1 === currentIndex
                  ? "bg-fifth text-[#F6F6F6]"
                  : "text-gray-300 hover:bg-[#af9f33] hover:text-white",
                "group flex items-center px-2 w-[90%] py-2 text-sm sm:text-lg lg:text-xl font-medium rounded-md"
              )}
            >
              <PowerSettingsNewIcon
                className={classNames(
                  -1 === currentIndex ? "text-fourth" : "text-fifth",
                  "mr-4 flex-shrink-0 text-sm sm:text-lg lg:text-xl h-6 w-6"
                )}
                aria-hidden="true"
              />
              {"log out"}
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}

export default DashBoardSideNav;

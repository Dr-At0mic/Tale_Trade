import { useState } from "react";
import {
  Bars3Icon,
} from "@heroicons/react/24/outline";
import BodyLayout from "./BodyLayout";
import DashBoardSideNav from "../shared/DashBoardSideNav";

export default function DashBoardLayout() {
  const [sidebarOpen, setSidebarOpen] = useState<boolean>(false);

  return (
    <>
      <div>
        <DashBoardSideNav sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        <div className="flex flex-1 flex-col md:pl-64 ">
          <div className="absolute top-0 z-10 bg-secondary pl-1 pb-1 pt-1 sm:pl-3 w-[100%] sm:pt-3 md:hidden">
            <button
              type="button"
              className="-ml-0.5 -mt-0.5 inline-flex h-12 w-12 items-center justify-center rounded-md text-fourth hover:text-[#af9f33] transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-[#FCDD1F]"
              onClick={() => setSidebarOpen(true)}
            >
              <span className="sr-only">Open sidebar</span>
              <Bars3Icon className="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <BodyLayout />
        </div>
      </div>
    </>
  );
}

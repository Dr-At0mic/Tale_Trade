import { Outlet } from "react-router-dom";

function BodyLayout() {
  return (
    <>
      <main className="flex-1 bg-third">
          <div className="w-[100%]">
            <div className="min-h-screen flex w-screen md:w-auto ">
              <div className="mt-12 md:mt-0 p-3 w-[100%]">
              <Outlet />
              </div>
            </div>
          </div>
      </main>
    </>
  );
}

export default BodyLayout;

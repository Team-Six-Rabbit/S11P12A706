import { createBrowserRouter } from "react-router-dom";

import App from "@/App";
import LoginPage from "@/pages/LoginPage";
import LiveBoardPage from "@/pages/LiveBoardPage";

const router = createBrowserRouter([
	{
		path: "/",
		element: <App />,
	},
	{
		path: "/login",
		element: <LoginPage />,
	},
	{
		path: "/firework",
		element: <LiveBoardPage />,
	},
]);

export default router;

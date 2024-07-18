import { createBrowserRouter } from "react-router-dom";

import App from "@/App";
import LiveBoardPage from "@/pages/LiveBoardPage";

const router = createBrowserRouter([
	{
		path: "/",
		element: <App />,
	},
	{
		path: "/live-board",
		element: <LiveBoardPage />,
	},
]);

export default router;

import fireIcon from "@/assets/images/Fire.png";
import BoardCategory from "@/components/common/Board/BoardCategory";
import RadioButtonGroup from "@/components/common/Board/RadioButtonGroup"; // 추가

interface BoardHeaderProps {
	boardName: string;
	categories: { id: string; name: string }[];
	selectedCategory: string;
	onCategorySelect: (category: string) => void;
	selectedStatus: "live" | "upcoming" | "ended"; // 수정
	onStatusSelect: (status: "live" | "upcoming" | "ended") => void; // 수정
}

function BoardHeader({
	boardName,
	categories,
	selectedCategory,
	onCategorySelect,
	selectedStatus,
	onStatusSelect,
}: BoardHeaderProps) {
	return (
		<div className="board-header flex flex-col w-full space-y-4">
			<div className="flex items-center justify-between space-x-9">
				<div className="flex items-center space-x-3 flex-shrink-0">
					<h1 className="text-5xl whitespace-nowrap">{boardName}</h1>
					<img src={fireIcon} alt="Board Icon" className="h-14" />
				</div>
				<div className="flex-grow">
					<BoardCategory
						categories={categories}
						onCategorySelect={onCategorySelect}
						selectedCategory={selectedCategory}
					/>
				</div>
			</div>
			<div className="flex items-center justify-between mt-4">
				<h2 className="text-3xl">#{selectedCategory}</h2>
				<h2 className="text-3xl">#{selectedStatus}</h2>
				<RadioButtonGroup
					selectedStatus={selectedStatus}
					onStatusSelect={onStatusSelect}
				/>
			</div>
		</div>
	);
}

export default BoardHeader;

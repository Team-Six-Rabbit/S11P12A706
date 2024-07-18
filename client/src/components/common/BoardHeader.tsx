import BoardCategory from "@/components/common/BoardCategory";
import fireIcon from "@/assets/images/Fire.png";

interface BoardHeaderProps {
	boardName: string;
	categories: { id: string; name: string }[];
	selectedCategory: string;
	onCategorySelect: (category: string) => void;
}

function BoardHeader({
	boardName,
	categories,
	selectedCategory,
	onCategorySelect,
}: BoardHeaderProps) {
	return (
		<div className="board-header flex items-center justify-between w-full space-x-9">
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
	);
}

export default BoardHeader;

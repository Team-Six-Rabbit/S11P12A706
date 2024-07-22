import fireIcon from "@/assets/images/Fire.png";
import BoardCategory from "@/components/common/Board/BoardCategory";
import RadioButtonGroup from "@/components/common/Board/RadioButtonGroup";
import Carousel from "@/components/common/Board/Carousel";

interface BoardHeaderProps {
	boardName: string;
	categories: { id: string; name: string }[];
	selectedCategory: string;
	onCategorySelect: (category: string) => void;
	selectedStatus: "live" | "upcoming" | "ended";
	onStatusSelect: (status: "live" | "upcoming" | "ended") => void;
	boardIcon?: string;
}

function BoardHeader({
	boardName,
	categories,
	selectedCategory,
	onCategorySelect,
	selectedStatus,
	onStatusSelect,
	boardIcon,
}: BoardHeaderProps) {
	return (
		<div className="board-header flex flex-col w-full space-y-4">
			<div className="flex items-center justify-between space-x-9">
				<div className="flex items-center space-x-3 flex-shrink-0">
					<h1 className="text-5xl whitespace-nowrap">{boardName}</h1>
					<img src={boardIcon} alt="Board Icon" className="h-14" />
				</div>
				<div className="flex-grow">
					<BoardCategory
						categories={categories}
						onCategorySelect={onCategorySelect}
						selectedCategory={selectedCategory}
					/>
				</div>
			</div>
			{selectedStatus === "live" && (
				<div
					style={{
						display: "flex",
						justifyContent: "center",
						marginBottom: "2rem",
					}}
				>
					<Carousel />
				</div>
			)}
			<div className="flex items-center justify-between mt-4">
				<h2 className="text-3xl">#{selectedCategory}</h2>
				<RadioButtonGroup
					selectedStatus={selectedStatus}
					onStatusSelect={onStatusSelect}
				/>
			</div>
		</div>
	);
}

// 설정된 기본 속성값 정의
BoardHeader.defaultProps = {
	boardIcon: fireIcon,
};

export default BoardHeader;

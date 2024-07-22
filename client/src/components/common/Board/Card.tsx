import React from "react";

interface CardProps {
	image: string;
	title: string;
	description: string;
	status: "live" | "upcoming" | "ended";
	viewers: number;
	liveTime?: string; // 라이브 진행 시간 선택 사항으로 수정
	index: number; // 인덱스를 props로 추가
}

const getStatusColor = (
	status: "live" | "upcoming" | "ended",
	index: number,
) => {
	if (status === "live") return "bg-transparent";
	if (status === "ended") return "bg-gray-500";
	const colors = ["bg-orange", "bg-blue", "bg-yellow", "bg-olive"];
	return colors[index % colors.length];
};
const getStatusText = (status: "live" | "upcoming" | "ended") => {
	switch (status) {
		case "live":
			return "라이브";
		case "upcoming":
			return "예정된 라이브";
		case "ended":
			return "종료된 라이브";
		default:
			return "";
	}
};

interface CustomCSSProperties extends React.CSSProperties {
	textShadow?: string;
}

const liveStateBorder: CustomCSSProperties = {
	textShadow:
		"-3px -3px 0 black, 3px -3px 0 black, -3px 3px 0 black, 3px 3px 0 black",
};

function Card({
	image,
	title,
	description,
	status,
	viewers,
	liveTime,
	index,
}: CardProps) {
	return (
		<div className="relative flex flex-col border-solid border-black border-4 shadow-md rounded-xl overflow-hidden hover:shadow-lg hover:-translate-y-1 transition-all duration-300 max-w-sm">
			<div className="h-44 relative overflow-hidden">
				<img src={image} alt={title} className="w-full h-full object-cover" />
				{status === "live" && (
					<div className="absolute top-2 left-2 bg-red-600 text-white px-2 py-1 rounded z-10">
						{getStatusText(status)}
					</div>
				)}
				{status !== "live" && (
					<>
						<div
							className={`absolute inset-0 ${getStatusColor(
								status,
								index,
							)} opacity-75 flex items-center justify-center`}
						/>
						<div
							className="absolute inset-0 flex items-center justify-center text-white text-3xl"
							style={liveStateBorder}
						>
							{getStatusText(status)}
						</div>
					</>
				)}
			</div>
			<div className="bg-white py-4 px-3 border-solid border-t-4">
				<h3 className="text-xl mb-2 font-medium">{title}</h3>
				<div className="flex justify-between items-center">
					<p className="text-base text-black">{description}</p>
					{status === "upcoming" && liveTime && (
						<div className="text-sm text-black">{liveTime}</div>
					)}
					{status === "live" && (
						<div className="text-sm text-black">{viewers}명 시청중</div>
					)}
				</div>
			</div>
		</div>
	);
}

// defaultProps 설정
Card.defaultProps = {
	liveTime: "",
};

export default Card;

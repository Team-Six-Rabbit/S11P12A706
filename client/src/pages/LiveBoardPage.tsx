import { useState, useEffect } from "react";
import Header from "@/components/header";
import BoardHeader from "@/components/common/Board/BoardHeader";
import Card from "@/components/common/Board/Card";

const categories = [
	{ id: "all", name: "전체" },
	{ id: "love", name: "사랑" },
	{ id: "daily", name: "일상" },
	{ id: "food", name: "음식" },
	{ id: "entertainment", name: "연예" },
	{ id: "games", name: "게임" },
	{ id: "sports", name: "스포츠" },
	{ id: "others", name: "기타" },
];

interface CardType {
	id: string;
	image: string;
	title: string;
	description: string;
	status: "live" | "upcoming" | "ended";
	viewers: number;
	liveTime?: string;
}

function LiveBoardPage() {
	const [selectedCategory, setSelectedCategory] = useState<string>("전체");
	const [selectedStatus, setSelectedStatus] = useState<
		"live" | "upcoming" | "ended"
	>("live");
	const [filteredCards, setFilteredCards] = useState<CardType[]>([]);

	const handleCategorySelect = (category: string) => {
		setSelectedCategory(category);
	};

	const handleStatusSelect = (status: "live" | "upcoming" | "ended") => {
		setSelectedStatus(status);
	};

	useEffect(() => {
		const sampleCards: CardType[] = [
			{
				id: "1",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "live",
				viewers: 32,
			},
			{
				id: "2",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "upcoming",
				viewers: 32,
				liveTime: "2024.07.12 14:00",
			},
			{
				id: "3",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "upcoming",
				viewers: 32,
				liveTime: "2024.07.13 14:00",
			},
			{
				id: "4",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "upcoming",
				viewers: 32,
				liveTime: "2024.07.14 14:00",
			},
			{
				id: "5",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "upcoming",
				viewers: 32,
				liveTime: "2024.07.15 14:00",
			},
			{
				id: "6",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "ended",
				viewers: 32,
			},
			{
				id: "7",
				image: "https://picsum.photos/400/400",
				title: "A 주장 VS B 주장",
				description: "대결자 A",
				status: "live",
				viewers: 32,
			},
		];

		setFilteredCards(
			sampleCards.filter((card) => card.status === selectedStatus),
		);
	}, [selectedStatus]);

	return (
		<div>
			<Header />
			<div className="p-10 pt-20">
				<BoardHeader
					boardName="불구경"
					categories={categories}
					selectedCategory={selectedCategory}
					onCategorySelect={handleCategorySelect}
					selectedStatus={selectedStatus}
					onStatusSelect={handleStatusSelect}
				/>
				<div className="mt-4 grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-4 gap-6">
					{filteredCards.map((card, index) => (
						<Card
							key={card.id}
							image={card.image}
							title={card.title}
							description={card.description}
							status={card.status}
							viewers={card.viewers}
							liveTime={card.liveTime}
							index={index}
						/>
					))}
				</div>
			</div>
		</div>
	);
}

export default LiveBoardPage;
